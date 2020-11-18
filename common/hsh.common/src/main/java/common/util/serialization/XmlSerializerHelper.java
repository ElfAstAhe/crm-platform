package common.util.serialization;

import org.jooq.tools.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Хелпер сериализации/десериализации xml
 *
 * @author elf
 */
public class XmlSerializerHelper {
    private XmlSerializerHelper() {
        // hide constructor
    }

    public static String serialize(Object source) {
        if (source == null)
            return null;

        try {
            JAXBContext ctx = JAXBContext.newInstance(source.getClass());
            Marshaller m = ctx.createMarshaller();
            Writer writer = new StringWriter();
            m.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.displayName());
            m.marshal(source, writer);
            return writer.toString();
        } catch(JAXBException ex) {
            return null;
        }
    }

    public static String serializeForHuman(Object source) {
        return serialize(source);
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(String data, Class<T> clazz) {
        if (StringUtils.isBlank(data))
            return null;
        try {
            Reader reader = new StringReader(data);
            JAXBContext ctx = JAXBContext.newInstance(clazz);
            Unmarshaller um = ctx.createUnmarshaller();
            return (T) um.unmarshal(reader);
        } catch (JAXBException ex) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T clone(T instance) {
        if (instance == null)
            return null;

        return (T) deserialize(serialize(instance), instance.getClass());
    }
}
