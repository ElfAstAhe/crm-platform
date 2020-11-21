package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestStandUtils {
    public static final String NULL_VALUE = "<null>";

    private TestStandUtils() {
        // hide constructor
    }

    public static TestSerializeClass buildInstance(Integer id, String name) {
        TestSerializeClass result = new TestSerializeClass();
        result.setId(id);
        result.setName(name);
        return result;
    }

    public static TestSerializeClass buildEmptyInstance() {
        return buildInstance(null, null);
    }

    public static TestSerializeClass buildSimpleInstance() {
        return buildInstance(1, "test");
    }

    public static TestSerializeClass buildSimpleInstance2() {
        return buildInstance(2, "test2");
    }

    public static List<TestSerializeClass> buildInstanceList() {
        return new ArrayList<>(Arrays.asList(buildSimpleInstance(), buildSimpleInstance2()));
    }
}
