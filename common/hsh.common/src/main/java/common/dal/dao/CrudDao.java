package common.dal.dao;

import common.dal.entity.IdEntity;

import java.util.List;
import java.util.concurrent.Future;

/**
 * crud dao
 * @param <Entity>  сущность
 * @param <Key>     ключ уникальности или id
 * @author elf
 */
public interface CrudDao<Entity extends IdEntity, Key> {
    /**
     * select entity by id
     * @param id id
     * @return entity
     */
    Entity find(Object id);
    /**
     * select entity async
     * @param id id
     * @return future
     */
    Future<Entity> findAsync(Object id);

    /**
     * select entity by unique key
     * @param key key
     * @return entity
     */
    Entity findByKey(Key key);
    /**
     * select entity by business key async
     * @param key key
     * @return future
     */
    Future<Entity> findByKeyAsync(Key key);

    /**
     * select all entities data
     * @return entity list
     */
    List<Entity> listAll();
    /**
     * select all entities data async
     * @return future
     */
    Future<List<Entity>> listAllAsync();

    /**
     * get list by conditions
     * @param params search params
     * @return entity list
     */
    List<Entity> listFiltered(FilterParams params);
    /**
     * Получить список по критериям поиска async
     * @param params condition list
     * @return future
     */
    Future<List<Entity>> listFilteredAsync(FilterParams params);

    boolean exists(Object id);
    Future<Boolean> existsAsync(Object id);

    /**
     * insert entity
     * @param entity entity
     * @return entity
     */
    Entity create(Entity entity);
    /**
     * insert entity async
     * @param entity entity
     * @return future
     */
    Future<Entity> createAsync(Entity entity);

    /**
     * update entity
     * @param entity entity
     * @return entity
     */
    Entity edit(Entity entity);
    /**
     * update entity async
     * @param entity entity
     * @return future
     */
    Future<Entity> editAsync(Entity entity);

    /**
     * remove entity
     * @param entity entity
     */
    void remove(Entity entity);

    /**
     * remove entity async
     * @param entity entity
     */
    void removeAsync(Entity entity);

    /**
     * get row count
     * @return row count
     */
    Long count();
    /**
     * Получить кол-во записей async
     * @return row count
     */
    Future<Long> countAsync();

    Long count(FilterParams params);

    Future<Long> countAsync(FilterParams params);
}
