package common.bll.cache;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Базовый класс простого кэша на основе HashMap класса
 * ВНИМАНИЕ!!! Наследники должный быть EJB, concurrencyType.BEAN
 *
 * @author elf
 * @param <Key>
 * @param <Value>
 */
public class BaseSimpleCache<Key, Value> implements SimpleCache<Key, Value>{
    private final Map<Key, Value> cacheValues = new HashMap<>();

    @Override
    public synchronized Value get(Key key) {
        return cacheValues.getOrDefault(key, null);
    }

    @Override
    @Asynchronous
    public Future<Value> getAsync(Key key) {
        return new AsyncResult<>(get(key));
    }

    @Override
    public synchronized List<Value> getAll() {
        return new ArrayList<>(cacheValues.values());
    }

    @Override
    @Asynchronous
    public Future<List<Value>> getAllAsync() {
        return new AsyncResult<>(getAll());
    }

    @Override
    public synchronized void put(Key key, Value value) {
        if (key == null || value == null)
            return;

        cacheValues.put(key, value);
    }

    @Override
    @Asynchronous
    public void putAsync(Key key, Value value) {
        put(key, value);
    }

    @Override
    public synchronized void remove(Key key) {
        if (key == null)
            return;

        cacheValues.remove(key);
    }

    @Override
    @Asynchronous
    public void removeAsync(Key key) {
        remove(key);
    }

    @Override
    public synchronized void clear() {
        cacheValues.clear();
    }

    @Override
    @Asynchronous
    public void clearAsync() {
        clear();
    }

    protected Map<Key, Value> getCacheValues() {
        return cacheValues;
    }
}
