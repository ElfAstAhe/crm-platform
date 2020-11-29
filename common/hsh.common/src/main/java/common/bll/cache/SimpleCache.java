package common.bll.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Function;

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
     * Добавить или заменить все значения
     *
     * @param values значения
     */
    void putAll(List<Value> values);
    void putAll(Map<Key, Value> values);

    /**
     * Добавить или заменить все значения
     *
     * @param values значения
     */
    void putAllAsync(List<Value> values);
    void putAllAsync(Map<Key, Value> values);
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
