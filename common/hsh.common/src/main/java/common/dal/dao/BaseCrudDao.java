package common.dal.dao;

import common.dal.entity.Identity;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.concurrent.Future;

/**
 * base crud dao, все наследники должны быть ejb
 *
 * @param <Entity> entity
 * @param <Key>    business key
 * @author elf
 */
public abstract class BaseCrudDao<Entity extends Identity, Key> implements CrudDao<Entity, Key> {
    private final Class<Entity> entityClass;

    public BaseCrudDao(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * insert entity async
     *
     * @param entity entity
     * @return future
     */
    @Override
    @Asynchronous
    public Future<Entity> createAsync(Entity entity) {
        return new AsyncResult<>(this.create(entity));
    }

    /**
     * insert entity
     *
     * @param entity entity
     * @return future
     */
    @Override
    public Entity create(Entity entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    /**
     * update entity async
     *
     * @param entity entity
     * @return future
     */
    @Override
    @Asynchronous
    public Future<Entity> editAsync(Entity entity) {
        return new AsyncResult<>(this.edit(entity));
    }

    /**
     * update entity
     *
     * @param entity entity
     * @return updated entity
     */
    @Override
    public Entity edit(Entity entity) {
        return getEntityManager().merge(entity);
    }

    /**
     * remove entity async
     *
     * @param entity entity
     */
    @Override
    @Asynchronous
    public void removeAsync(Entity entity) {
        this.remove(entity);
    }

    /**
     * remove entity
     *
     * @param entity entity
     */
    @Override
    public void remove(Entity entity) {
        Entity _entity = entity;
        if (!getEntityManager().contains(entity))
            _entity = getEntityManager().merge(entity);
        getEntityManager().remove(_entity);
    }

    /**
     * select entity async
     *
     * @param id id
     * @return future
     */
    @Override
    @Asynchronous
    public Future<Entity> findAsync(Object id) {
        return new AsyncResult<>(this.find(id));
    }

    /**
     * select entity
     *
     * @param id id
     * @return entity
     */
    @Override
    public Entity find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public Entity findByKey(Key key) {
        return this.find(key);
    }

    @Override
    @Asynchronous
    public Future<Entity> findByKeyAsync(Key key) {
        return new AsyncResult<>(this.findByKey(key));
    }

    /**
     * select all entities data async
     *
     * @return future
     */
    @Override
    @Asynchronous
    public Future<List<Entity>> listAllAsync() {
        return new AsyncResult<>(this.listAll());
    }

    /**
     * select all entities data
     *
     * @return entity list
     */
    @Override
    public List<Entity> listAll() {
        CriteriaQuery<Entity> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq = cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Получить список по критериям поиска async
     *
     * @param conditions condition list
     * @return future
     */
    @Override
    @Asynchronous
    public Future<List<Entity>> listAsync(ListConditions conditions) {
        return new AsyncResult<>(this.list(conditions));
    }

    /**
     * get list by conditions
     *
     * @param conditions condition list
     * @return entity list
     */
    @Override
    public List<Entity> list(ListConditions conditions) {
        CriteriaQuery<Entity> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        TypedQuery<Entity> q = getEntityManager().createQuery(cq);
        q.setMaxResults(conditions.getRowCount() - conditions.getFromRow() + 1);
        q.setFirstResult(conditions.getFromRow());
        return q.getResultList();
    }

    /**
     * Получить кол-во записей async
     *
     * @return row count
     */
    @Override
    @Asynchronous
    public Future<Long> countAsync() {
        return new AsyncResult<>(this.count());
    }

    /**
     * get row count
     *
     * @return row count
     */
    @Override
    public Long count() {
        CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        cq = cq.select(getEntityManager().getCriteriaBuilder().count(cq.from(entityClass)));
        TypedQuery<Long> q = getEntityManager().createQuery(cq);
        return (q.getSingleResult());
    }

    protected Class<Entity> getEntityClass() {
        return entityClass;
    }

    protected abstract EntityManager getEntityManager();
}
