package common.helpers.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExceptionDtoBuilderTest {
    @Test
    public void get_shouldReturnBuilderInstance() {
        // prepare
        // act
        ExceptionDtoBuilder actual = ExceptionDtoBuilder.get();
        // assert
        Assertions.assertNotNull(actual);
    }

    @Test
    public void get_shouldReturnNewBuilderInstanceAlways() {
        // prepare
        // act
        ExceptionDtoBuilder actual1 = ExceptionDtoBuilder.get();
        ExceptionDtoBuilder actual2 = ExceptionDtoBuilder.get();
        // assert
        Assertions.assertNotNull(actual1);
        Assertions.assertNotNull(actual2);
        Assertions.assertNotSame(actual1, actual2);
    }

    @Test
    public void setException_shouldSetAttrAndReturnSameBuilder() {
        // prepare
        ExceptionDtoBuilder expectedBuilder = ExceptionDtoBuilder.get();
        String expectedData = "test";
        // act
        ExceptionDtoBuilder actualBuilder = expectedBuilder.setException(expectedData);
        String actualData = actualBuilder.build().getException();
        // assert
        Assertions.assertNotNull(actualBuilder);
        Assertions.assertNotNull(actualData);
        Assertions.assertEquals(expectedData, actualData);
        Assertions.assertSame(expectedBuilder, actualBuilder);
    }

    @Test
    public void setMessage_shouldSetAttrAndReturnSameBuilder() {
        // prepare
        ExceptionDtoBuilder expectedBuilder = ExceptionDtoBuilder.get();
        String expectedData = "test";
        // act
        ExceptionDtoBuilder actualBuilder = expectedBuilder.setMessage(expectedData);
        String actualData = actualBuilder.build().getMessage();
        // assert
        Assertions.assertNotNull(actualBuilder);
        Assertions.assertNotNull(actualData);
        Assertions.assertEquals(expectedData, actualData);
        Assertions.assertSame(expectedBuilder, actualBuilder);
    }

    @Test
    public void setStackTrace_shouldSetAttrAndReturnSameBuilder() {
        // prepare
        ExceptionDtoBuilder expectedBuilder = ExceptionDtoBuilder.get();
        String expectedData = "test";
        // act
        ExceptionDtoBuilder actualBuilder = expectedBuilder.setStackTrace(expectedData);
        String actualData = actualBuilder.build().getStackTrace();
        // assert
        Assertions.assertNotNull(actualBuilder);
        Assertions.assertNotNull(actualData);
        Assertions.assertEquals(expectedData, actualData);
        Assertions.assertSame(expectedBuilder, actualBuilder);
    }

}