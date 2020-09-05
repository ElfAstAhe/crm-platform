package common.bll.cache;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Интерфейс кэша последних значений
 *
 * @param <Key>   ключ
 * @param <Value> значение
 *
 * @author elf
 */
public interface SimpleCache<Key, Value> {

    /**
     * Получить 1 значение по ключу
     *
     * @param key ключ
     * @return модель
     */
    Value get(Key key);

    /**
     * Получить 1 значение по ключу
     *
     * @param key ключ
     * @return модель
     */
    Future<Value> getAsync(Key key);

    /**
     * Получить список значений
     *
     * @return список моделей
     */
    List<Value> getAll();

    /**
     * Получить список значений
     *
     * @return список моделей
     */
    Future<List<Value>> getAllAsync();

    /**
     * Добавить или заменить модель
     *
     * @param value значение
     */
    void put(Key key, Value value);

    /**
     * Добавить или заменить модель
     *
     * @param value значение
     */
    void putAsync(Key key, Value value);

    /**
     * Удалить
     *
     * @param key ключ
     */
    void remove(Key key);

    /**
     * Удалить
     *
     * @param key ключ
     */
    void removeAsync(Key key);

    /**
     * Очистить
     */
    void clear();

    /**
     * Очистить
     */
    void clearAsync();
}
