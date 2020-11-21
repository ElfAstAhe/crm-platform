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
import java.util.Arrays;
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

    @Test
    public void putAll_useNull_ShouldDoNothing() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        // act
        cache.putAll(null, TestSerializeClass::getId);
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void putAllAsync_shouldPutAllValues() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        List<TestSerializeClass> expected = TestStandUtils.buildInstanceList();
        // act
        cache.putAllAsync(expected, TestSerializeClass::getId);
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.not(Matchers.empty()));
        MatcherAssert.assertThat(actual, Matchers.hasSize(2));
        MatcherAssert.assertThat(actual, Matchers.containsInAnyOrder(expected.get(0), expected.get(1)));
    }

    @Test
    public void remove_valueExists_shouldRemoveValue() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        TestSerializeClass data = TestStandUtils.buildSimpleInstance();
        cache.put(data.getId(), data);
        // act
        cache.remove(data.getId());
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void remove_valueNotExists_shouldDoNothing() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        TestSerializeClass data = TestStandUtils.buildSimpleInstance();
        TestSerializeClass data2 = TestStandUtils.buildSimpleInstance2();
        cache.put(data.getId(), data);
        // act
        cache.remove(data2.getId());
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.hasSize(1));
    }

    @Test
    public void remove_emptyKey_shouldDoNothing() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        TestSerializeClass data = TestStandUtils.buildSimpleInstance();
        cache.put(data.getId(), data);
        // act
        cache.remove(null);
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.hasSize(1));
    }

    @Test
    public void removeAsync_shouldRemoveValue() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        TestSerializeClass data1 = TestStandUtils.buildSimpleInstance();
        TestSerializeClass data2 = TestStandUtils.buildSimpleInstance2();
        cache.putAll(Arrays.asList(data1, data2), TestSerializeClass::getId);
        // act
        cache.removeAsync(data1.getId());
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.not(Matchers.empty()));
        MatcherAssert.assertThat(actual, Matchers.not(Matchers.hasItem(data1)));
    }

    @Test
    public void clear_shouldClearValues() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        TestSerializeClass data1 = TestStandUtils.buildSimpleInstance();
        TestSerializeClass data2 = TestStandUtils.buildSimpleInstance2();
        cache.putAll(Arrays.asList(data1, data2), TestSerializeClass::getId);
        // act
        cache.clear();
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void clearAsync_shouldClearValues() {
        // prepare
        TestBaseSimpleCache cache = new TestBaseSimpleCache();
        TestSerializeClass data1 = TestStandUtils.buildSimpleInstance();
        TestSerializeClass data2 = TestStandUtils.buildSimpleInstance2();
        cache.putAll(Arrays.asList(data1, data2), TestSerializeClass::getId);
        // act
        cache.clearAsync();
        List<TestSerializeClass> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

}
