package common.util.serialization;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 * Хелпер сериализации/десериализации json
 *
 * @author elf
 */
public class JsonSerializerHelper {
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

    private JsonSerializerHelper() {
        // hide constructor
    }
}
