package common.util.serialization;

import org.jooq.tools.StringUtils;
import org.yaml.snakeyaml.Yaml;

/**
 * Хелпер сериализации/десериализации yaml
 *
 * @author elf
 */
public class YamlSerializerHelper {
    private YamlSerializerHelper() {
        // hide constructor
    }

    public static String serialize(Object source) {
        if (source == null)
            return null;

        return new Yaml().dump(source);
    }

    public static String serializeForHuman(Object source) {
        return serialize(source);
    }

    public static <T> T deserialize(String data, Class<T> clazz) {
        if (StringUtils.isBlank(data))
            return null;

        return new Yaml().loadAs(data, clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T clone(T instance) {
        if (instance == null)
            return null;

        return (T) deserialize(serialize(instance), instance.getClass());
    }
}
