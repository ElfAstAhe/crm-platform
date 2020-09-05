package common.util.cryptography;

import common.exceptions.cryptography.CryptoUtilException;
import common.util.cryptography.digest.DigestUtil;
import org.jooq.tools.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AesCryptoUtil implements CryptoUtil {
    private static final byte[] cbcSalt = {(byte) 0x11, (byte) 0x22, (byte) 0xaa, (byte) 0xbb,
            (byte) 0x33, (byte) 0x44, (byte) 0xcc, (byte) 0xdd,
            (byte) 0x55, (byte) 0x66, (byte) 0xee, (byte) 0xff,
            (byte) 0x77, (byte) 0x88, (byte) 0x99, (byte) 0x00};
    private static final String ALGORITHM_AES = "AES";
    private static final String ALGORITHM_AES_CBC_PKCS5 = "AES/CBC/PKCS5Padding";
    private static final String ALGORITHM_AES_ECB_PKCS5 = "AES/ECB/PKCS5Padding";

    private final String algorithm;

    public static CryptoUtil getAesCbcInstance() {
        return new AesCryptoUtil(AesCryptoUtil.ALGORITHM_AES_CBC_PKCS5);
    }

    public static CryptoUtil getAesEcbInstance() {
        return new AesCryptoUtil(AesCryptoUtil.ALGORITHM_AES_ECB_PKCS5);
    }

    @Override
    public String encrypt(String data, String key) throws CryptoUtilException {
        if (StringUtils.isBlank(data))
            throw new CryptoUtilException("Nothing to encrypt");
        return Base64.getEncoder()
                .encodeToString(encrypt(data.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public String decrypt(String data, String key) throws CryptoUtilException {
        if (StringUtils.isBlank(data))
            throw new CryptoUtilException("Nothing to decrypt");
        return new String(decrypt(Base64.getDecoder()
                                        .decode(data), key.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    @Override
    public byte[] decrypt(byte[] data, byte[] key) throws CryptoUtilException {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(DigestUtil.encodeDigest(key, DigestUtil.DIGEST_SHA256), ALGORITHM_AES);
            Cipher dCipher = Cipher.getInstance(algorithm);
            initCipher(dCipher, Cipher.DECRYPT_MODE, secretKeySpec);
            return dCipher.doFinal(data);
        } catch (Throwable ex) {
            throw new CryptoUtilException("Error decrypting data", ex);
        }
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) throws CryptoUtilException {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(DigestUtil.encodeDigest(key, DigestUtil.DIGEST_SHA256), ALGORITHM_AES);
            Cipher eCipher = Cipher.getInstance(algorithm);
            initCipher(eCipher, Cipher.ENCRYPT_MODE, secretKeySpec);
            return eCipher.doFinal(data);
        } catch (Throwable ex) {
            throw new CryptoUtilException("Error encrypting data", ex);
        }
    }

    private AesCryptoUtil(String algorithm) {
        this.algorithm = algorithm;
    }

    private void initCipher(Cipher cipher, int mode, SecretKeySpec secretKeySpec)
            throws
            InvalidKeyException,
            InvalidAlgorithmParameterException,
            NoSuchAlgorithmException {
        switch (cipher.getAlgorithm()) {
            case ALGORITHM_AES_ECB_PKCS5: {
                cipher.init(mode, secretKeySpec);
                break;
            }
            case ALGORITHM_AES_CBC_PKCS5: {
                cipher.init(mode, secretKeySpec, new IvParameterSpec(cbcSalt));
                break;
            }
            default:
                throw new NoSuchAlgorithmException("Unsupported algorithm");
        }
    }
}
