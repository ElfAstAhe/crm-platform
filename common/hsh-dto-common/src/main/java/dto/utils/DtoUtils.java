package dto.utils;

import java.lang.reflect.Field;
import java.util.*;

public class DtoUtils {
    public static final String NULL_STRING = "<null>";

    private DtoUtils() {
        // hide constructor
    }

    public static String nullOrValue(Object value) {
        return value == null ? NULL_STRING : String.valueOf(value);
    }

    public static String buildKeyValue(String key, Object value) {
        return String.format("%s=[%s]", key, nullOrValue(value));
    }

    public static String buildToString(Object instance) {
        if (instance == null)
            return null;

        final StringJoiner builder = new StringJoiner(",", instance.getClass().getName() + "[", "]");

        getFieldsRecursive(instance.getClass()).forEach(f -> {
            f.setAccessible(true);
            try {
                builder.add(buildKeyValue(f.getName(), f.get(instance)));
            } catch (IllegalAccessException e) {
                //
            }
        });

        return builder.toString();
    }

    private static List<Field> getFieldsRecursive(Class<?> instance) {
        if (instance == null)
            return Collections.emptyList();

        ArrayList<Field> result = new ArrayList<>(Arrays.asList(instance.getDeclaredFields()));
        result.addAll(getFieldsRecursive(instance.getSuperclass()));

        return result;
    }
}
