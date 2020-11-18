package common.util.cryptography;

import common.exceptions.cryptography.CryptoUtilException;
import common.util.cryptography.digest.DigestUtil;
import org.jooq.tools.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class DesCryptoUtil implements CryptoUtil {
    public static final String ALGORITHM_DES = "DES";
    public static final String ALGORITHM_DESEDE = "DESede";

    private final String algorithm;

    private DesCryptoUtil(String algorithm) {
        this.algorithm = algorithm;
    }

    public static CryptoUtil getDesInstance() {
        return new DesCryptoUtil(DesCryptoUtil.ALGORITHM_DES);
    }

    public static CryptoUtil getDesedeInstance() {
        return new DesCryptoUtil(DesCryptoUtil.ALGORITHM_DESEDE);
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
            KeySpec keySpec = buildKeySpec(algorithm, key);
            SecretKey secretKey = SecretKeyFactory.getInstance(algorithm).generateSecret(keySpec);
            Cipher dCipher = Cipher.getInstance(algorithm);
            dCipher.init(Cipher.DECRYPT_MODE, secretKey);
            return dCipher.doFinal(data);
        } catch (Throwable ex) {
            throw new CryptoUtilException("Error decrypting data", ex);
        }
    }

    @Override
    public byte[] encrypt(byte[] data, byte[] key) throws CryptoUtilException {
        try {
            KeySpec keySpec = buildKeySpec(algorithm, key);
            SecretKey secretKey = SecretKeyFactory.getInstance(algorithm).generateSecret(keySpec);
            Cipher eCipher = Cipher.getInstance(algorithm);
            eCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return eCipher.doFinal(data);
        } catch (Throwable ex) {
            throw new CryptoUtilException("Error encrypting data", ex);
        }
    }

    private KeySpec buildKeySpec(String algorithm, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
        switch (algorithm) {
            case DesCryptoUtil.ALGORITHM_DES:
                return new DESKeySpec(DigestUtil.encodeDigest(key, DigestUtil.DIGEST_SHA256));
            case DesCryptoUtil.ALGORITHM_DESEDE:
                return new DESedeKeySpec(DigestUtil.encodeDigest(key, DigestUtil.DIGEST_SHA256));
            default:
                throw new NoSuchAlgorithmException("Unsupported algorithm");
        }
    }
}
