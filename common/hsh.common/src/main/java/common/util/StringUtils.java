package common.util;

import org.apache.commons.lang3.stream.Streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author elf
 */
public class StringUtils {
    public static final String NULL_STRING = "<null>";
    public static final String PREFIX_TEMPLATE = "%s[";
    public static final String SUFFIX = "]";
    public static final String DELIMITER = ",";
    
    public static String formatNull(String formatString, Object... args) {
        return String.format(formatString,
                Arrays.stream(args)
                .map(i -> i == null ? NULL_STRING : i)
                .toArray());
    }

    public static String toNullString(Object arg) {
        return arg != null ? String.valueOf(arg) : NULL_STRING;
    }

    public static String buildKeyValue(String key, Object value) {
        return String.format("%s=[%s]", key, toNullString(value));
    }

    public static String buildPrefix(Object instance) {
        if (instance == null)
            return null;

        return String.format(PREFIX_TEMPLATE, instance.getClass().getName());
    }

    public static String toIndentedString(Object o) {
        if (o == null)
            return NULL_STRING;
        
        return o.toString().replace("\n", "\n    ");
    }

    /**
     * read resource text file into string
     *
     * @param clazz class
     * @param filePath resource file path in META-INF dir ..
     * @param charset default utf-8
     * @return String
     */
    public static String readResourceTextFile(Class<?> clazz, String filePath, Charset charset) {
        if (clazz == null)
            return null;
        if (org.apache.commons.lang3.StringUtils.isBlank(filePath))
            return null;
        Charset correctedCharset = charset;
        if (charset == null)
            correctedCharset = StandardCharsets.UTF_8;

        StringBuilder sb = new StringBuilder(8192);
        try (BufferedReader is = new BufferedReader(
                new InputStreamReader(
                        clazz.getResourceAsStream(filePath), correctedCharset))) {
            String line;
            while ((line = is.readLine()) != null) {
                if (!line.startsWith("-")) {
                    sb.append(line);
                    sb.append('\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
