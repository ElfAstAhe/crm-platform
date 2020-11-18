package common.dto.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.TestSerializeClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseBuilderTest {
    @Test
    public void getInstance_shouldReturnInstance() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // prepare
        TestSerializerClassBuilder builder = new TestSerializerClassBuilder();
        Method meth = builder.getClass().getSuperclass().getDeclaredMethod("getInstance");
        meth.setAccessible(true);
        // act
        TestSerializeClass actual = (TestSerializeClass)meth.invoke(builder);
        // assert
        Assertions.assertNotNull(actual);
    }

    @Test
    public void build_shouldReturnInstance() {
        // prepare
        TestSerializerClassBuilder builder = new TestSerializerClassBuilder();
        // act
        TestSerializeClass actual = builder.build();
        // assert
        Assertions.assertNotNull(actual);
    }
}