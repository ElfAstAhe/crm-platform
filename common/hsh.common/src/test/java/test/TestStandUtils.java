package test;

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
}
