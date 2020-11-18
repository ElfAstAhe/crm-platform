package common.util.serialization;

import org.apache.commons.lang3.StringUtils;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

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
        if (source == null)
            return null;
        Jsonb builder = JsonbBuilder.create();

        return builder.toJson(source);
    }

    public static String serializeForHuman(Object source) {
        if (source == null)
            return null;
        JsonbConfig config = new JsonbConfig().withFormatting(true);
        Jsonb builder = JsonbBuilder.create(config);

        return builder.toJson(source);
    }

    public static <T> T deserialize(String data, Class<T> clazz) {
        if (StringUtils.isBlank(data))
            return null;
        Jsonb builder = JsonbBuilder.create();
        return builder.fromJson(data, clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T clone(T instance) {
        if (instance == null)
            return null;
        return (T) deserialize(serialize(instance), instance.getClass());
    }
}
