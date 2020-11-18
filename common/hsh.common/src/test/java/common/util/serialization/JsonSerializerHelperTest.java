package common.util.serialization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.TestSerializeClass;

class JsonSerializerHelperTest {
    // common
    // {"id":1,"name":"test"}

    // for human
    // {
    //     "id": 1,
    //     "name": "test"
    // }

    @Test
    public void serialize_passInstance_shouldReturnSerializedString() {
        // prepare
        TestSerializeClass data = buildInstance();
        // act
        String actual = JsonSerializerHelper.serialize(data);
        // assert
        Assertions.assertNotNull(actual);
        System.out.println(actual);
    }

    @Test
    public void serialize_passNull_shouldReturnNull() {
        // prepare
        // act
        // assert
        Assertions.assertNull(JsonSerializerHelper.serialize(null));
    }

    @Test
    public void serializeForHuman_passInstance_shouldReturnSerializedString() {
        // prepare
        TestSerializeClass data = buildInstance();
        // act
        String actual = JsonSerializerHelper.serializeForHuman(data);
        String[] actualStrings = actual.split("\n");
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actualStrings.length > 1);
        System.out.println(actual);
    }

    @Test
    public void serializeForHuman_passNull_shouldReturnNull() {
        // prepare
        // act
        // assert
        Assertions.assertNull(JsonSerializerHelper.serializeForHuman(null));
    }

    @Test
    public void deserialize_passString_shouldReturnInstance() {
        // prepare
        String data = "{\"id\":1,\"name\":\"test\"}";
        int expectedId = 1;
        String expectedName = "test";
        // act
        TestSerializeClass actual = JsonSerializerHelper.deserialize(data, TestSerializeClass.class);
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
        Assertions.assertNull(JsonSerializerHelper.deserialize(null, TestSerializeClass.class));
    }

    @Test
    public void clone_passInstance_shouldReturnClone() {
        // prepare
        TestSerializeClass expected = buildInstance();
        // act
        TestSerializeClass actual = JsonSerializerHelper.clone(expected);
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
        Assertions.assertNull(JsonSerializerHelper.clone(null));
    }

    private TestSerializeClass buildInstance() {
        TestSerializeClass inst = new TestSerializeClass();
        inst.setId(1);
        inst.setName("test");
        return inst;
    }
}