package common.dal.dao;

import common.dal.entity.Identity;

import java.util.List;
import java.util.concurrent.Future;

/**
 * crud dao
 * @param <Entity>  сущность
 * @param <Key>     ключ уникальности или id
 * @author elf
 */
public interface CrudDao<Entity extends Identity, Key> {
    /**
     * select entity by id
     * @param id id
     * @return entity
     */
    Entity find(Object id);
  
    /**
     * select entity by unique key
     * @param key key
     * @return entity
     */
    Entity findByKey(Key key);

    /**
     * get list by conditions
     * @param conditions condition list
     * @return entity list
     */
    List<Entity> list(ListConditions conditions);

    /**
     * select all entities data
     * @return entity list
     */
    List<Entity> listAll();

    /**
     * insert entity
     * @param entity entity
     * @return entity
     */
    Entity create(Entity entity);
    
    /**
     * update entity
     * @param entity entity
     * @return entity
     */
    Entity edit(Entity entity);
    
    /**
     * remove entity
     * @param entity entity
     */
    void remove(Entity entity);

    /**
     * get row count
     * @return row count
     */
    Long count();

    /**
     * select entity async
     * @param id id
     * @return future
     */
    Future<Entity> findAsync(Object id);

    /**
     * select entity by business key async
     * @param key key
     * @return future
     */
    Future<Entity> findByKeyAsync(Key key);

    /**
     * Получить список по критериям поиска async
     * @param conditions condition list
     * @return future
     */
    Future<List<Entity>> listAsync(ListConditions conditions);
    
    /**
     * select all entities data async
     * @return future
     */
    Future<List<Entity>> listAllAsync();

    /**
     * insert entity async
     * @param entity entity
     * @return future
     */
    Future<Entity> createAsync(Entity entity);
    
    /**
     * update entity async
     * @param entity entity
     * @return future
     */
    Future<Entity> editAsync(Entity entity);
      
    /**
     * remove entity async
     * @param entity entity
     */
    void removeAsync(Entity entity);
    
    /**
     * Получить кол-во записей async
     * @return row count
     */
    Future<Long> countAsync();
}
