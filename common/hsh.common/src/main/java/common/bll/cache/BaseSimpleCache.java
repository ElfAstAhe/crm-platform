package common.bll.cache;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Базовый класс простого кэша на основе HashMap класса
 * ВНИМАНИЕ!!! Наследники должный быть EJB
 *
 * @author elf
 * @param <Key>
 * @param <Value>
 */
public abstract class BaseSimpleCache<Key, Value> implements SimpleCache<Key, Value>{
    private final Map<Key, Value> cacheValues = new ConcurrentHashMap<>();

    @PostConstruct
    public void postConstruct() {
        List<Value> values = initCache();
        if (values != null)
            values.forEach(v -> cacheValues.put(buildKey(v), v));
    }

    @PreDestroy
    public void preDestroy() {
        cacheValues.clear();
    }

    @Override
    public Value get(Key key) {
        if(key == null)
            return null;

        return cacheValues.getOrDefault(key, null);
    }

    @Override
    @Asynchronous
    public Future<Value> getAsync(Key key) {
        return new AsyncResult<>(get(key));
    }

    @Override
    public List<Value> getAll() {
        return new ArrayList<>(cacheValues.values());
    }

    @Override
    @Asynchronous
    public Future<List<Value>> getAllAsync() {
        return new AsyncResult<>(getAll());
    }

    @Override
    public void put(Key key, Value value) {
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
    public void putAll(List<Value> values) {
        if (values == null)
            return;

        putAll(values.stream()
                .collect(Collectors.toMap(this::buildKey, v -> v)));
    }

    @Override
    public void putAll(Map<Key, Value> values) {
        if (values == null || values.isEmpty())
            return;

        values.forEach(this::put);
    }

    @Override
    @Asynchronous
    public void putAllAsync(Map<Key, Value> values) {
        putAll(values);
    }

    @Override
    @Asynchronous
    public void putAllAsync(List<Value> values) {
        putAll(values);
    }

    @Override
    public void remove(Key key) {
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
    public void clear() {
        cacheValues.clear();
    }

    @Override
    @Asynchronous
    public void clearAsync() {
        clear();
    }

    /**
     * for override
     *
     * @return initable list of cache values
     */
    protected List<Value> initCache() {
        return Collections.emptyList();
    }

    protected Map<Key, Value> getCacheValues() {
        return cacheValues;
    }

    protected abstract Key buildKey(Value value);
}
