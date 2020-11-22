package common.util.serialization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.MockDtoEntity;
import test.TestStandUtils;

public class XmlSerializerHelperTest {
    // <?xml version="1.0" encoding="UTF-8" standalone="yes"?><testSerializeClass><id>1</id><name>test</name></testSerializeClass>

    @Test
    public void serialize_passInstance_shouldReturnString() {
        // prepare
        MockDtoEntity data = TestStandUtils.buildSimpleInstance();
        // act
        String actual = XmlSerializerHelper.serialize(data);
        // assert
        Assertions.assertNotNull(actual);
        System.out.println(actual);
    }

    @Test
    public void serialize_passNull_shouldReturnNull() {
        // prepare
        // act
        // assert
        Assertions.assertNull(XmlSerializerHelper.serialize(null));
    }

    @Test
    public void serializeForHuman_passInstance_shouldReturnSerializedString() {
        // prepare
        MockDtoEntity data = TestStandUtils.buildSimpleInstance();
        // act
        String actual = XmlSerializerHelper.serializeForHuman(data);
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
        Assertions.assertNull(XmlSerializerHelper.serializeForHuman(null));
    }

    @Test
    public void deserialize_passString_shouldReturnInstance() {
        // prepare
        String data = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><testSerializeClass><id>1</id><name>test</name></testSerializeClass>";
        int expectedId = 1;
        String expectedName = "test";
        // act
        MockDtoEntity actual = XmlSerializerHelper.deserialize(data, MockDtoEntity.class);
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
        Assertions.assertNull(XmlSerializerHelper.deserialize(null, MockDtoEntity.class));
    }

    @Test
    public void clone_passInstance_shouldReturnInstance() {
        // prepare
        MockDtoEntity expected = TestStandUtils.buildSimpleInstance();
        // act
        MockDtoEntity actual = XmlSerializerHelper.clone(expected);
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
        Assertions.assertNull(XmlSerializerHelper.clone(null));
    }
}