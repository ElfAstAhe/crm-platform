package common.util.serialization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.TestSerializeClass;
import test.TestStandUtils;

public class YamlSerializerHelperTest {
    // !!test.TestSerializeClass {id: 1, name: test}

    @Test
    public void serialize_passInstance_shouldReturnString() {
        // prepare
        TestSerializeClass data = TestStandUtils.buildSimpleInstance();
        // act
        String actual = YamlSerializerHelper.serialize(data);
        // assert
        Assertions.assertNotNull(actual);
        System.out.println(actual);
    }

    @Test
    public void serialize_passNull_shouldReturnNull() {
        // prepare
        // act
        // assert
        Assertions.assertNull(YamlSerializerHelper.serialize(null));
    }

    @Test
    public void serializeForHuman_passInstance_shouldReturnSerializedString() {
        // prepare
        TestSerializeClass data = TestStandUtils.buildSimpleInstance();
        // act
        String actual = YamlSerializerHelper.serializeForHuman(data);
        String[] actualStrings = actual.split("\n");
        // assert
        Assertions.assertNotNull(actual);
//        Assertions.assertTrue(actualStrings.length > 1);
//        System.out.println(actual);
    }

    @Test
    public void serializeForHuman_passNull_shouldReturnNull() {
        // prepare
        // act
        // assert
        Assertions.assertNull(YamlSerializerHelper.serializeForHuman(null));
    }

    @Test
    public void deserialize_passString_shouldReturnInstance() {
        // prepare
        String data = "!!test.TestSerializeClass {id: 1, name: test}";
        int expectedId = 1;
        String expectedName = "test";
        // act
        TestSerializeClass actual = YamlSerializerHelper.deserialize(data, TestSerializeClass.class);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedId, actual.getId());
        Assertions.assertEquals(expectedName, actual.getName());
    }

    @Test
    public void deserialize_passNull_shouldReturnNull() {
        // prepare
        // act
        // assert
        Assertions.assertNull(YamlSerializerHelper.deserialize(null, TestSerializeClass.class));
    }

    @Test
    public void clone_passInstance_shouldReturnInstance() {
        // prepare
        TestSerializeClass expected = TestStandUtils.buildSimpleInstance();
        // act
        TestSerializeClass actual = YamlSerializerHelper.clone(expected);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertNotSame(expected, actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void clone_passNull_shouldReturnNull() {
        // prepare
        // act
        // assert
        Assertions.assertNull(YamlSerializerHelper.clone(null));
    }
}