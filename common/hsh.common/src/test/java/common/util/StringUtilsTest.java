package common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.MockSimpleEntity;
import test.TestStandUtils;

class StringUtilsTest {
    @Test
    public void toNullString_nullParam_shouldReturnNullRepresentation() {
        // prepare
        // act
        String actual = StringUtils.toNullString(null);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(StringUtils.NULL_STRING, actual);
    }

    @Test
    public void toNullString_paramNotNull_shouldReturnStringRepresentation() {
        // prepare
        String expected = "123";
        // act
        String actual = StringUtils.toNullString(expected);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void formatNull_useNullParams_shouldReturnWithNullRepresentation() {
        // prepare
        String data = "123";
        String formatString = "%s -- %s";
        String expected = "<null> -- 123";
        // act
        String actual = StringUtils.formatNull(formatString, null, data);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void buildKeyValue_shouldReturnKeyValueRepresentation() {
        // prepare
        String expected = "id=[123]";
        String key = "id";
        String value = "123";
        // act
        String actual = StringUtils.buildKeyValue(key, value);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void buildPrefix_nullParam_shouldReturnNull() {
        // prepare
        MockSimpleEntity data = null;
        // act
        String actual = StringUtils.buildPrefix(data);
        // assert
        Assertions.assertNull(actual);
    }

    @Test
    public void buildPrefix_paramNotNull_shouldReturnStringRepresentation() {
        // prepare
        MockSimpleEntity data = TestStandUtils.buildSimpleInstance();
        String expected = data.getClass().getName() + "[";
        // act
        String actual = StringUtils.buildPrefix(data);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }
}