package common.dto.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.MockSimpleEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseBuilderTest {
    @Test
    public void getInstance_shouldReturnInstance() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // prepare
        MockSimpleEntityBuilder builder = new MockSimpleEntityBuilder();
        Method meth = builder.getClass().getSuperclass().getDeclaredMethod("getInstance");
        meth.setAccessible(true);
        // act
        MockSimpleEntity actual = (MockSimpleEntity)meth.invoke(builder);
        // assert
        Assertions.assertNotNull(actual);
    }

    @Test
    public void build_shouldReturnInstance() {
        // prepare
        MockSimpleEntityBuilder builder = new MockSimpleEntityBuilder();
        // act
        MockSimpleEntity actual = builder.build();
        // assert
        Assertions.assertNotNull(actual);
    }
}