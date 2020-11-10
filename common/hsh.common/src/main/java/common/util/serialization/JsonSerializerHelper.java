package common.util.serialization;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 * Хелпер сериализации/десериализации json
 *
 * @author elf
 */
public class JsonSerializerHelper {
    private JsonSerializerHelper() {
        // hide constructor
    }

    public static String serialize(Object source) {
        Jsonb builder = JsonbBuilder.create();
        return builder.toJson(source);
    }

    public static String serializeForHuman(Object source) {
        Jsonb builder = JsonbBuilder.create();
        return builder.toJson(source);
    }

    public static <T> T deserialize(String data, Class<T> clazz) {
        Jsonb builder = JsonbBuilder.create();
        return builder.fromJson(data, clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T clone(Object instance) {
        return (T) deserialize(serialize(instance), instance.getClass());
    }
}
