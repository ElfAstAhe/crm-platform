package common.bll.cache;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import test.MockSimpleEntity;
import test.TestStandUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class BaseSimpleCacheTest {
    @Test
    public void initCache_shouldReturnEmptyList() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        // act
        List<MockSimpleEntity> actual = cache.initCache();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void postConstruct_shouldInitCacheEmptyValues() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        // act
        cache.postConstruct();
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void preDestroy_shouldEmptyCache() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        cache.putAll(TestStandUtils.buildInstanceList());
        // act
        cache.preDestroy();
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void getCacheValues_shouldReturnInstance() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        Method method = cache.getClass().getSuperclass().getDeclaredMethod("getCacheValues");
        method.setAccessible(true);
        // act
        // assert
        Assertions.assertNotNull(method.invoke(cache));
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    @NullSource()
    public void get_valueNotExists_shouldReturnNull(Long key) {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        // act
        MockSimpleEntity actual = cache.get(key);
        // assert
        Assertions.assertNull(actual);
    }

    @Test
    public void get_valueExists_shouldReturnValue() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockSimpleEntity expected = TestStandUtils.buildSimpleInstance();
        cache.put(expected.getId(), expected);
        // act
        MockSimpleEntity actual = cache.get(expected.getId());
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
        Assertions.assertSame(expected, actual);
    }

    @Test
    public void getAll_valuesNotExists_shouldReturnEmptyList() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        // cache.putAll(TestStandUtils.buildInstanceList(), TestSerializeClass::getId);
        // act
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void getAll_valuesExists_shouldReturnList() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        cache.putAll(TestStandUtils.buildInstanceList());
        // act
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.not(Matchers.empty()));
    }

    @Test
    public void getAsync_shouldReturnTask() throws ExecutionException, InterruptedException {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        cache.putAll(TestStandUtils.buildInstanceList());
        // act
        Future<MockSimpleEntity> actual = cache.getAsync(1L);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.get().getId());
    }

    @Test
    public void getAllAsync_shouldReturnTask() throws ExecutionException, InterruptedException {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        // act
        Future<List<MockSimpleEntity>> actual = cache.getAllAsync();
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get());
    }

    @Test
    public void put_shouldPutValue() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockSimpleEntity data = TestStandUtils.buildSimpleInstance();
        // act
        cache.put(data.getId(), data);
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.size(), 1);
        Assertions.assertSame(data, actual.get(0));
    }

    @ParameterizedTest
    @ValueSource(longs = {1L})
    @NullSource
    public void put_useEmptyData_shouldDoNothing(Long key) {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockSimpleEntity value = null;
        if (key == null)
            value = TestStandUtils.buildSimpleInstance();
        // act
        cache.put(key, value);
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void putAsync_shouldPutValue() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockSimpleEntity data = TestStandUtils.buildSimpleInstance();
        // act
        cache.putAsync(data.getId(), data);
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.hasSize(1));
        MatcherAssert.assertThat(actual, Matchers.hasItem(data));
    }

    @Test
    public void putAll_useValues_shouldPutAllValues() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        List<MockSimpleEntity> expected = TestStandUtils.buildInstanceList();
        // act
        cache.putAll(expected);
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.not(Matchers.empty()));
        MatcherAssert.assertThat(actual, Matchers.hasSize(2));
        MatcherAssert.assertThat(actual, Matchers.containsInAnyOrder(expected.get(0), expected.get(1)));
    }

    @Test
    public void putAll_useNull_ShouldDoNothing() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        // act
        cache.putAll((List<MockSimpleEntity>)null);
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void putAllAsync_shouldPutAllValues() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        List<MockSimpleEntity> expected = TestStandUtils.buildInstanceList();
        // act
        cache.putAllAsync(expected);
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.not(Matchers.empty()));
        MatcherAssert.assertThat(actual, Matchers.hasSize(2));
        MatcherAssert.assertThat(actual, Matchers.containsInAnyOrder(expected.get(0), expected.get(1)));
    }

    @Test
    public void remove_valueExists_shouldRemoveValue() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockSimpleEntity data = TestStandUtils.buildSimpleInstance();
        cache.put(data.getId(), data);
        // act
        cache.remove(data.getId());
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void remove_valueNotExists_shouldDoNothing() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockSimpleEntity data = TestStandUtils.buildSimpleInstance();
        MockSimpleEntity data2 = TestStandUtils.buildSimpleInstance2();
        cache.put(data.getId(), data);
        // act
        cache.remove(data2.getId());
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.hasSize(1));
    }

    @Test
    public void remove_emptyKey_shouldDoNothing() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockSimpleEntity data = TestStandUtils.buildSimpleInstance();
        cache.put(data.getId(), data);
        // act
        cache.remove(null);
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.hasSize(1));
    }

    @Test
    public void removeAsync_shouldRemoveValue() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockSimpleEntity data1 = TestStandUtils.buildSimpleInstance();
        MockSimpleEntity data2 = TestStandUtils.buildSimpleInstance2();
        cache.putAll(Arrays.asList(data1, data2));
        // act
        cache.removeAsync(data1.getId());
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.not(Matchers.empty()));
        MatcherAssert.assertThat(actual, Matchers.not(Matchers.hasItem(data1)));
    }

    @Test
    public void clear_shouldClearValues() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockSimpleEntity data1 = TestStandUtils.buildSimpleInstance();
        MockSimpleEntity data2 = TestStandUtils.buildSimpleInstance2();
        cache.putAll(Arrays.asList(data1, data2));
        // act
        cache.clear();
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void clearAsync_shouldClearValues() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockSimpleEntity data1 = TestStandUtils.buildSimpleInstance();
        MockSimpleEntity data2 = TestStandUtils.buildSimpleInstance2();
        cache.putAll(Arrays.asList(data1, data2));
        // act
        cache.clearAsync();
        List<MockSimpleEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

}
