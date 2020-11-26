package microservice.common.dal.dao;

import common.dal.entity.IdEntity;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Интерфейс dao крайних(актуальных) значений
 *
 * @param <Key>    search key
 * @param <Entity> entity class
 * @author elf
 */
public interface LastValuesDao<Entity extends IdEntity, Key extends Serializable> {
    /**
     * Получить 1 последнее значение по ключу
     *
     * @param key ключ
     * @return entity
     */
    Entity findLast(Key key);

    /**
     * Получить 1 последнее значение по ключу (async)
     *
     * @param key ключ
     * @return entity
     */
    Future<Entity> findLastAsync(Key key);

    /**
     * Получить список последних значений
     *
     * @param key      ключ
     * @param rowCount кол-во строк
     * @return список последних значений
     */
    List<Entity> listLast(Key key, int rowCount);

    /**
     * Получить список последних значений (async)
     *
     * @param key      ключ
     * @param rowCount кол-во строк
     * @return список последних значений
     */
    Future<List<Entity>> listLastAsync(Key key, int rowCount);
}

