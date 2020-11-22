package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestStandUtils {
    public static final String NULL_VALUE = "<null>";

    private TestStandUtils() {
        // hide constructor
    }

    public static MockDtoEntity buildInstance(Integer id, String name) {
        MockDtoEntity result = new MockDtoEntity();
        result.setId(id);
        result.setName(name);
        return result;
    }

    public static MockDtoEntity buildEmptyInstance() {
        return buildInstance(null, null);
    }

    public static MockDtoEntity buildSimpleInstance() {
        return buildInstance(1, "test");
    }

    public static MockDtoEntity buildSimpleInstance2() {
        return buildInstance(2, "test2");
    }

    public static List<MockDtoEntity> buildInstanceList() {
        return new ArrayList<>(Arrays.asList(buildSimpleInstance(), buildSimpleInstance2()));
    }
}
