package common.bll.cache;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.jooq.tools.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import test.MockDtoEntity;
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
        List<MockDtoEntity> actual = cache.initCache();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void postConstruct_shouldInitCacheEmptyValues() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        cache.putAll(TestStandUtils.buildInstanceList());
        // act
        cache.postConstruct();
        List<MockDtoEntity> actual = cache.getAll();
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
        List<MockDtoEntity> actual = cache.getAll();
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
    @ValueSource(strings = {"1", TestStandUtils.NULL_VALUE})
    public void get_valueNotExists_shouldReturnNull(String key) {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        Integer prepKey;
        if (StringUtils.equals(TestStandUtils.NULL_VALUE, key))
            prepKey = null;
        else
            prepKey = Integer.parseInt(key);
        // act
        MockDtoEntity actual = cache.get(prepKey);
        // assert
        Assertions.assertNull(actual);
    }

    @Test
    public void get_valueExists_shouldReturnValue() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockDtoEntity expected = TestStandUtils.buildSimpleInstance();
        cache.put(expected.getId(), expected);
        // act
        MockDtoEntity actual = cache.get(expected.getId());
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
        List<MockDtoEntity> actual = cache.getAll();
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
        List<MockDtoEntity> actual = cache.getAll();
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
        Future<MockDtoEntity> actual = cache.getAsync(1);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.get().getId());
    }

    @Test
    public void getAllAsync_shouldReturnTask() throws ExecutionException, InterruptedException {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        // act
        Future<List<MockDtoEntity>> actual = cache.getAllAsync();
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get());
    }

    @Test
    public void put_shouldPutValue() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockDtoEntity data = TestStandUtils.buildSimpleInstance();
        // act
        cache.put(data.getId(), data);
        List<MockDtoEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.size(), 1);
        Assertions.assertSame(data, actual.get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"data", TestStandUtils.NULL_VALUE})
    public void put_useEmptyData_shouldDoNothing(String flag) {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        Integer key = null;
        MockDtoEntity value = TestStandUtils.buildSimpleInstance();
        if (StringUtils.equals(TestStandUtils.NULL_VALUE, flag)) {
            key = 1;
            value = null;
        }
        // act
        cache.put(key, value);
        List<MockDtoEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void putAsync_shouldPutValue() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockDtoEntity data = TestStandUtils.buildSimpleInstance();
        // act
        cache.putAsync(data.getId(), data);
        List<MockDtoEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.hasSize(1));
        MatcherAssert.assertThat(actual, Matchers.hasItem(data));
    }

    @Test
    public void putAll_useValues_shouldPutAllValues() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        List<MockDtoEntity> expected = TestStandUtils.buildInstanceList();
        // act
        cache.putAll(expected);
        List<MockDtoEntity> actual = cache.getAll();
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
        cache.putAll((List<MockDtoEntity>)null);
        List<MockDtoEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void putAllAsync_shouldPutAllValues() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        List<MockDtoEntity> expected = TestStandUtils.buildInstanceList();
        // act
        cache.putAllAsync(expected);
        List<MockDtoEntity> actual = cache.getAll();
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
        MockDtoEntity data = TestStandUtils.buildSimpleInstance();
        cache.put(data.getId(), data);
        // act
        cache.remove(data.getId());
        List<MockDtoEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void remove_valueNotExists_shouldDoNothing() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockDtoEntity data = TestStandUtils.buildSimpleInstance();
        MockDtoEntity data2 = TestStandUtils.buildSimpleInstance2();
        cache.put(data.getId(), data);
        // act
        cache.remove(data2.getId());
        List<MockDtoEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.hasSize(1));
    }

    @Test
    public void remove_emptyKey_shouldDoNothing() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockDtoEntity data = TestStandUtils.buildSimpleInstance();
        cache.put(data.getId(), data);
        // act
        cache.remove(null);
        List<MockDtoEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.hasSize(1));
    }

    @Test
    public void removeAsync_shouldRemoveValue() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockDtoEntity data1 = TestStandUtils.buildSimpleInstance();
        MockDtoEntity data2 = TestStandUtils.buildSimpleInstance2();
        cache.putAll(Arrays.asList(data1, data2));
        // act
        cache.removeAsync(data1.getId());
        List<MockDtoEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.not(Matchers.empty()));
        MatcherAssert.assertThat(actual, Matchers.not(Matchers.hasItem(data1)));
    }

    @Test
    public void clear_shouldClearValues() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockDtoEntity data1 = TestStandUtils.buildSimpleInstance();
        MockDtoEntity data2 = TestStandUtils.buildSimpleInstance2();
        cache.putAll(Arrays.asList(data1, data2));
        // act
        cache.clear();
        List<MockDtoEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

    @Test
    public void clearAsync_shouldClearValues() {
        // prepare
        MockBaseSimpleCache cache = new MockBaseSimpleCache();
        MockDtoEntity data1 = TestStandUtils.buildSimpleInstance();
        MockDtoEntity data2 = TestStandUtils.buildSimpleInstance2();
        cache.putAll(Arrays.asList(data1, data2));
        // act
        cache.clearAsync();
        List<MockDtoEntity> actual = cache.getAll();
        // assert
        Assertions.assertNotNull(actual);
        MatcherAssert.assertThat(actual, Matchers.empty());
    }

}
