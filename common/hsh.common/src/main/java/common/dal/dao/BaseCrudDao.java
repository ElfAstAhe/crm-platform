package common.dal.dao;

import common.dal.entity.IdEntity;
import org.jooq.tools.StringUtils;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * base crud dao, все наследники должны быть ejb
 *
 * @param <Entity> entity
 * @param <Key>    id or unique key
 * @author elf
 */
public abstract class BaseCrudDao<Entity extends IdEntity, Key extends Serializable>
        implements CrudDao<Entity, Key> {
    private static final Logger logger = Logger.getLogger(BaseCrudDao.class.getName());

    private final Class<Entity> entityClass;
    private final DaoHelper<Entity> daoHelper;

    public BaseCrudDao(Class<Entity> entityClass)          {
        this(entityClass, new DaoHelper<>(entityClass, false));
    }

    public BaseCrudDao(Class<Entity> entityClass, DaoHelper<Entity> daoHelper) {
        this.entityClass = entityClass;
        this.daoHelper = daoHelper;
    }

    @Override
    @Asynchronous
    public Future<Entity> findAsync(Object id) {
        return new AsyncResult<>(this.find(id));
    }

    @Override
    public Entity find(Object id) {
        if (id == null)
            return null;

        return getEntityManager().find(entityClass, id);
    }

    @Override
    public Entity findByKey(Key key) {
        if (key == null)
            return null;

        return find(key);
    }

    @Override
    @Asynchronous
    public Future<Entity> findByKeyAsync(Key key) {
        return new AsyncResult<>(this.findByKey(key));
    }

    @Override
    @Asynchronous
    public Future<List<Entity>> listAllAsync() {
        return new AsyncResult<>(this.listAll());
    }

    @Override
    public List<Entity> listAll() {
//        CriteriaQuery<Entity> cq = getEntityManager().getCriteriaBuilder()
//                .createQuery(entityClass);
//        cq = cq.select(cq.from(entityClass));
//        return getEntityManager().createQuery(cq)
//                .getResultList();
        return getEntityManager().createQuery("select e from " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    @Override
    public List<Entity> listFiltered(FilterParams params) {
//        CriteriaQuery<Entity> cq = getEntityManager().getCriteriaBuilder()
//                .createQuery(entityClass);
//        cq.select(cq.from(entityClass));
//        TypedQuery<Entity> q = getEntityManager().createQuery(cq);
//        q.setMaxResults(params.getRowCount() - params.getFromRow() + 1);
//        q.setFirstResult(params.getFromRow());
//        return q.getResultList();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Entity> cq = cb.createQuery(entityClass);
        Root<Entity> root = cq.from(entityClass);
        List<DaoUtils.JoinTable> joins = daoHelper.getJoins(root);
        setFetchings(root);

        cq.select(root);

        if (params.getSortOrder() != null && !StringUtils.isBlank(params.getField())) {
            if (params.getSortOrder().equals(SortOrderEnum.ASCENDING)) {
                cq.orderBy(cb.asc(daoHelper.createPathConditioning(root, joins, params.getField())));
            } else if (params.getSortOrder().equals(SortOrderEnum.DESCENDING)) {
                cq.orderBy(cb.desc(daoHelper.createPathConditioning(root, joins, params.getField())));
            }
        }

        cq.where(daoHelper.createWhere(cb, root, joins, params.getFilterSet()));
        TypedQuery<Entity> tq = getEntityManager().createQuery(cq)
                .setLockMode(LockModeType.NONE);

        if (params.getFromRow() != 0)
            tq.setFirstResult(params.getFromRow());
        if (params.getRowCount() != 0)
            tq.setMaxResults(params.getRowCount());

        List<Entity> result = tq.setLockMode(LockModeType.NONE)
                .getResultList();
        result.forEach(getEntityManager()::detach);

        return result;
    }

    @Override
    @Asynchronous
    public Future<List<Entity>> listFilteredAsync(FilterParams params) {
        return new AsyncResult<>(this.listFiltered(params));
    }

    @Override
    public boolean exists(Object id) {
        try {
            return getEntityManager().createQuery("select 1 from " +
                    DaoUtils.getEntityName(entityClass) + " e " +
                    "where e." + DaoUtils.getEntityIdFieldName(entityClass) + " = :id", Long.class)
                    .setParameter("id", id)
                    .setLockMode(LockModeType.NONE)
                    .setMaxResults(1)
                    .getSingleResult() != null;
        } catch (NoResultException ex) {
            return false;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error check entity instance existence", ex);
            return false;
        }
    }

    @Override
    @Asynchronous
    public Future<Boolean> existsAsync(Object id) {
        return new AsyncResult<>(exists(id));
    }

    @Override
    @Asynchronous
    public Future<Long> countAsync() {
        return new AsyncResult<>(this.count());
    }

    @Override
    public Long count() {
//
//        CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder()
//                .createQuery(Long.class);
//        cq = cq.select(getEntityManager().getCriteriaBuilder()
//                .count(cq.from(entityClass)));
//        TypedQuery<Long> q = getEntityManager().createQuery(cq);
//        return (q.getSingleResult());
        return count(new BaseFilterParams(0,
                0,
                DaoUtils.createFilterSet(Collections.emptyMap()),
                SortOrderEnum.UNSORTED,
                null));
    }

    @Override
    public Long count(FilterParams params) {
        boolean isFieldBlank = StringUtils.isBlank(params.getField());
        return countInternal(isFieldBlank ? null : params.getField(),
                params.getFilterSet(),
                isFieldBlank);
    }

    @Override
    @Asynchronous
    public Future<Long> countAsync(FilterParams params) {
        return new AsyncResult<>(count(params));
    }

    @Override
    @Asynchronous
    public Future<Entity> createAsync(Entity entity) {
        return new AsyncResult<>(this.create(entity));
    }

    @Override
    public Entity create(Entity entity) {
        if (entity == null)
            return null;
        getEntityManager().persist(entity);

        return entity;
    }

    @Override
    @Asynchronous
    public Future<Entity> editAsync(Entity entity) {
        return new AsyncResult<>(this.edit(entity));
    }

    @Override
    public Entity edit(Entity entity) {
        if (entity == null)
            return null;

        return getEntityManager().merge(entity);
    }

    @Override
    @Asynchronous
    public void removeAsync(Entity entity) {
        this.remove(entity);
    }

    @Override
    public void remove(Entity entity) {
//        if (entity == null)
//            return;
//        Entity _entity = entity;
//        if (!getEntityManager().contains(entity))
//            _entity = getEntityManager().merge(entity);
//        getEntityManager().remove(_entity);
        getEntityManager().createQuery("delete from " + entityClass.getSimpleName() + " e where " + DaoUtils.getEntityIdFieldName(entityClass) + " = :id")
                .setParameter("id", entity.getId())
                .executeUpdate();
    }

    protected Class<Entity> getEntityClass() {
        return entityClass;
    }

    protected DaoHelper<Entity> getDaoHelper() {
        return daoHelper;
    }

    /**
     * for overriding
     *
     * @param root root
     * @return expression
     */
    protected Expression<?> countByField(Root<Entity> root) {
        return null;
    }

    /**
     * for overriding
     *
     * @param root root
     */
    protected void setFetchings(Root<Entity> root) {
        // nothing
    }

    protected abstract EntityManager getEntityManager();

    private Long countInternal(String field, List<Map<String, Object>> filterSet, boolean isAutoField) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<Entity> root = cq.from(entityClass);
        List<DaoUtils.JoinTable> joins = daoHelper.getJoins(root);

        Expression<?> path;
        if (isAutoField) {
            path = countByField(root);
            if (path == null) {
                path = root;
            }
        } else {
            path = daoHelper.createPath(root, joins, field);
        }
        cq.select(cb.countDistinct(path));

        Predicate where = daoHelper.createWhere(cb, root, joins, filterSet);
        if (where != null) {
            cq.where(where);
        }

        return getEntityManager().createQuery(cq)
                .setLockMode(LockModeType.NONE)
                .getSingleResult();
    }
}
