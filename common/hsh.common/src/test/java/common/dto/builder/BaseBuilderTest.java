package common.dto.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.MockDtoEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseBuilderTest {
    @Test
    public void getInstance_shouldReturnInstance() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // prepare
        MockSerializerClassBuilder builder = new MockSerializerClassBuilder();
        Method meth = builder.getClass().getSuperclass().getDeclaredMethod("getInstance");
        meth.setAccessible(true);
        // act
        MockDtoEntity actual = (MockDtoEntity)meth.invoke(builder);
        // assert
        Assertions.assertNotNull(actual);
    }

    @Test
    public void build_shouldReturnInstance() {
        // prepare
        MockSerializerClassBuilder builder = new MockSerializerClassBuilder();
        // act
        MockDtoEntity actual = builder.build();
        // assert
        Assertions.assertNotNull(actual);
    }
}