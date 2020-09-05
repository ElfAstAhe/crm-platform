package common.dal.dao;

import common.dal.entity.BaseIdentity;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Интерфейс репозитория актуальных значений
 *
 * @param <TKey>
 * @param <TEntity>
 * @author elf
 */
public interface LastValuesDao<TKey, TEntity extends BaseIdentity> {
    /**
     * Получить 1 последнее значение по ключу
     *
     * @param key ключ
     * @return entity
     */
    TEntity findLast(TKey key);

    /**
     * Получить 1 последнее значение по ключу (async)
     *
     * @param key ключ
     * @return entity
     */
    Future<TEntity> findLastAsync(TKey key);

    /**
     * Получить список последних значений
     *
     * @param key ключ
     * @param rowCount кол-во строк
     * @return список последних значений
     */
    List<TEntity> listLast(TKey key, int rowCount);

    /**
     * Получить список последних значений (async)
     *
     * @param key ключ
     * @param rowCount кол-во строк
     * @return список последних значений
     */
    Future<List<TEntity>> listLastAsync(TKey key, int rowCount);
}

