package common.bll.cache;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.jooq.tools.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import test.TestSerializeClass;
import test.TestStandUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class BaseSimpleCacheTest {
    @Test
    public void getCacheValues_shouldReturnInstance() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        Method method = cache.getClass().getSuperclass().getDeclaredMethod("getCacheValues");
        method.setAccessible(true);
        // act
        // assert
        Assertions.assertNotNull(method.invoke(cache));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", TestStandUtils.NULL_VALUE})
    public void get_valueNotExists_shouldReturnNull(String key) {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        Integer prepKey;
        if (StringUtils.equals(TestStandUtils.NULL_VALUE, key))
            prepKey = null;
        else
            prepKey = Integer.parseInt(key);
        // act
        TestSerializeClass actual = cache.get(prepKey);
        // assert
        Assertions.assertNull(actual);
    }

    @Test
    public void get_valueExists_shouldReturnValue() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        TestSerializeClass expected = TestStandUtils.buildSimpleInstance();
        cache.put(expected.getId(), expected);
        // act
        TestSerializeClass actual = cache.get(expected.getId());
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
        Assertions.assertSame(expected, actual);
    }

    @Test
    public void getAll_valuesNotExists_shouldReturnEmptyList() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        // cache.putAll(TestStandUtils.buildInstanceList(), TestSerializeClass::getId);
        // act
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void getAll_valuesExists_shouldReturnList() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        cache.putAll(TestStandUtils.buildInstanceList(), TestSerializeClass::getId);
        // act
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.not(Matchers.empty()));
    }

    @Test
    public void getAsync_shouldReturnTask() throws ExecutionException, InterruptedException {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        cache.putAll(TestStandUtils.buildInstanceList(), TestSerializeClass::getId);
        // act
        Future<TestSerializeClass> actual = cache.getAsync(1);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.get().getId());
    }

    @Test
    public void getAllAsync_shouldReturnTask() throws ExecutionException, InterruptedException {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        // act
        Future<List<TestSerializeClass>> actual = cache.getAllAsync();
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get());
    }

    @Test
    public void put_shouldPutValue() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        TestSerializeClass data = TestStandUtils.buildSimpleInstance();
        // act
        cache.put(data.getId(), data);
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.size(), 1);
        Assertions.assertSame(data, actual.get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"data", TestStandUtils.NULL_VALUE})
    public void put_useEmptyData_shouldDoNothing(String flag) {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        Integer key = null;
        TestSerializeClass value = TestStandUtils.buildSimpleInstance();
        if (StringUtils.equals(TestStandUtils.NULL_VALUE, flag)) {
            key = 1;
            value = null;
        }
        // act
        cache.put(key, value);
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void putAsync_shouldPutValue() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        TestSerializeClass data = TestStandUtils.buildSimpleInstance();
        // act
        cache.putAsync(data.getId(), data);
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.hasSize(1));
        MatcherAssert.assertThat(actual, Matchers.hasItem(data));
    }

    @Test
    public void putAll_useValues_shouldPutAllValues() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        List<TestSerializeClass> expected = TestStandUtils.buildInstanceList();
        // act
        cache.putAll(expected, TestSerializeClass::getId);
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.not(Matchers.empty()));
        MatcherAssert.assertThat(actual, Matchers.hasSize(2));
        MatcherAssert.assertThat(actual, Matchers.containsInAnyOrder(expected.get(0), expected.get(1)));
    }
}