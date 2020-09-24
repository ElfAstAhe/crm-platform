package common.dal.dao;

import common.dal.entity.IdEntity;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

/**
 * base crud dao, все наследники должны быть ejb
 *
 * @param <Entity> entity
 * @param <Key>    id or unique key
 * @author elf
 */
public abstract class BaseCrudDao<Entity extends IdEntity, Key extends Serializable>
        implements CrudDao<Entity, Key> {
    private final Class<Entity> entityClass;
    private final DaoHelper<Entity> daoHelper;

    public BaseCrudDao(Class<Entity> entityClass) {
        this.entityClass = entityClass;
        this.daoHelper = new DaoHelper<>(entityClass, false);
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
        CriteriaQuery<Entity> cq = getEntityManager().getCriteriaBuilder()
                .createQuery(entityClass);
        cq.select(cq.from(entityClass));
        TypedQuery<Entity> q = getEntityManager().createQuery(cq);
        q.setMaxResults(params.getRowCount() - params.getFromRow() + 1);
        q.setFirstResult(params.getFromRow());
        return q.getResultList();
    }

    @Override
    @Asynchronous
    public Future<List<Entity>> listFilteredAsync(FilterParams params) {
        return new AsyncResult<>(this.listFiltered(params));
    }

    @Override
    @Asynchronous
    public Future<Long> countAsync() {
        return new AsyncResult<>(this.count());
    }

    @Override
    public Long count() {
        CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder()
                .createQuery(Long.class);
        cq = cq.select(getEntityManager().getCriteriaBuilder()
                .count(cq.from(entityClass)));
        TypedQuery<Long> q = getEntityManager().createQuery(cq);
        return (q.getSingleResult());
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

    protected abstract EntityManager getEntityManager();
}
