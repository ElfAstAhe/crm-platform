package common.util.serialization;

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

    public static String serializeOrThrow(Object source) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(source.getClass());
        Marshaller m = ctx.createMarshaller();
        Writer writer = new StringWriter();
        m.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8);
        m.marshal(source, writer);
        return writer.toString();
    }

    public static String serialize(Object source) {
        try {
            return serializeOrThrow(source);
        } catch(Exception ex) {
            return null;
        }
    }

    public static <T> T deserializeOrThrow(String data, Class<T> clazz) throws JAXBException {
        return deserializeOrThrow(new StringReader(data), clazz);
    }

    public static <T> T deserializeOrThrow(Reader reader, Class<T> clazz) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(clazz);
        Unmarshaller um = ctx.createUnmarshaller();
        return (T) um.unmarshal(reader);
    }

    public static <T> T deserializeOrThrow(InputStream stream, Class<T> clazz) throws JAXBException {
        return deserializeOrThrow(new InputStreamReader(stream), clazz);
    }

    public static <T> T deserialize(String data, Class<T> clazz) {
        try {
            return deserializeOrThrow(data, clazz);
        } catch (Exception ex) {
            return null;
        }
    }

    public static <T> T deserialize(Reader reader, Class<T> clazz) {
        try {
            return deserializeOrThrow(reader, clazz);
        } catch(Exception ex) {
            return null;
        }
    }

    public static <T> T deserialize(InputStream stream, Class<T> clazz) {
        try {
            return deserializeOrThrow(stream, clazz);
        } catch(Exception ex) {
            return null;
        }
    }
}
