package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestStandUtils {
    public static final String NULL_VALUE = "<null>";

    private TestStandUtils() {
        // hide constructor
    }

    public static MockSimpleEntity buildInstance(Long id, String name) {
        MockSimpleEntity result = new MockSimpleEntity();
        result.setId(id);
        result.setName(name);
        return result;
    }

    public static MockSimpleEntity buildEmptyInstance() {
        return buildInstance(null, null);
    }

    public static MockSimpleEntity buildSimpleInstance() {
        return buildInstance(1L, "test");
    }

    public static MockSimpleEntity buildSimpleInstance2() {
        return buildInstance(2L, "test2");
    }

    public static List<MockSimpleEntity> buildInstanceList() {
        return new ArrayList<>(Arrays.asList(buildSimpleInstance(), buildSimpleInstance2()));
    }
}
