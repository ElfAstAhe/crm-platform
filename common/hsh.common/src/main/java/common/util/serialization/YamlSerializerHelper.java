package common.util.serialization;

import org.yaml.snakeyaml.Yaml;

/**
 * Хелпер сериализации/десериализации yaml
 *
 * @author elf
 */
public class YamlSerializerHelper {

    public static String serialize(Object source) {
        return new Yaml().dump(source);
    }

    public static <T> T deserialize(String data, Class<T> clazz) {
        return new Yaml().loadAs(data, clazz);
    }

    private YamlSerializerHelper() {
        // hide constructor
    }
}
