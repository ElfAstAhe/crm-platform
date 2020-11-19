package common.bll.cache;

import org.jooq.tools.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import test.TestSerializeClass;
import test.TestStandUtils;

import java.beans.Expression;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
}