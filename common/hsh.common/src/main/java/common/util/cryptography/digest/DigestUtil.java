package common.util.cryptography.digest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SuppressWarnings("unused")
public class DigestUtil {
    public static final String DIGEST_MD2 = "MD2";
    public static final String DIGEST_MD5 = "MD5";
    public static final String DIGEST_SHA = "SHA";
    public static final String DIGEST_SHA224 = "SHA-224";
    public static final String DIGEST_SHA256 = "SHA-256";
    public static final String DIGEST_SHA384 = "SHA-384";
    public static final String DIGEST_SHA512 = "SHA-512";
    public static final String DIGEST_SHA512_224 = "SHA-512/224";
    public static final String DIGEST_SHA512_256 = "SHA-512/256";

    public static String encodeMD2(String data) throws NoSuchAlgorithmException {
        return Base64.getEncoder()
                .encodeToString(encodeDigest(data.getBytes(StandardCharsets.UTF_8), DIGEST_MD2));
    }

    public static String encodeMD5(String data) throws NoSuchAlgorithmException {
        return Base64.getEncoder()
                .encodeToString(encodeDigest(data.getBytes(StandardCharsets.UTF_8), DIGEST_MD5));
    }

    public static String encodeSHA(String data) throws NoSuchAlgorithmException {
        return Base64.getEncoder()
                .encodeToString(encodeDigest(data.getBytes(StandardCharsets.UTF_8), DIGEST_SHA));
    }

    public static String encodeSHA224(String data) throws NoSuchAlgorithmException {
        return Base64.getEncoder()
                .encodeToString(encodeDigest(data.getBytes(StandardCharsets.UTF_8), DIGEST_SHA224));
    }

    public static String encodeSHA256(String data) throws NoSuchAlgorithmException {
        return Base64.getEncoder()
                .encodeToString(encodeDigest(data.getBytes(StandardCharsets.UTF_8), DIGEST_SHA256));
    }

    public static String encodeSHA384(String data) throws NoSuchAlgorithmException {
        return Base64.getEncoder()
                .encodeToString(encodeDigest(data.getBytes(StandardCharsets.UTF_8), DIGEST_SHA384));
    }

    public static String encodeSHA512(String data) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-512");
////        md.update(value.getBytes("UTF-8"));
//        md.update(value.getBytes(StandardCharsets.UTF_8));
//        byte[] digest = md.digest();
//        return DatatypeConverter.printBase64Binary(digest);
        return Base64.getEncoder()
                .encodeToString(encodeDigest(data.getBytes(StandardCharsets.UTF_8), DIGEST_SHA512));
    }

    public static String encodeSHA512_224(String data) throws NoSuchAlgorithmException {
        return Base64.getEncoder()
                .encodeToString(encodeDigest(data.getBytes(StandardCharsets.UTF_8), DIGEST_SHA512_224));
    }

    public static String encodeSHA512_256(String data) throws NoSuchAlgorithmException {
        return Base64.getEncoder()
                .encodeToString(encodeDigest(data.getBytes(StandardCharsets.UTF_8), DIGEST_SHA512_256));
    }

    public static byte[] encodeDigest(byte[] data, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        return messageDigest.digest(data);
    }

    private DigestUtil() {
        // hide constructor
    }
}
