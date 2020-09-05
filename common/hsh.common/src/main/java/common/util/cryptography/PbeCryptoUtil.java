package common.util.cryptography;


public class PbeCryptoUtil {}

/*
public class PbeCryptoUtil implements CryptoUtil {
    public static final String ALGORITHM_PBE_MD5_DES = "PBEWithMD5AndDES";
    public static final String ALGORITHM_PBE_HMAC_AES = "PBEWithHmacSHA256AndAES_128";

    private final byte[] salt = {(byte) 0xAA, (byte) 0xBB, (byte) 0xCC, (byte) 0xDD,
            (byte) 0xEE, (byte) 0xFF, (byte) 0x11, (byte) 0x22};
    private final int iterationCount = 19;
    private final String algorithm;

    public PbeCryptoUtil(String algorithm) throws NoSuchAlgorithmException {
        if (StringUtils.isBlank(algorithm))
            throw new NullPointerException("Algorithm name unspecified");
        if (!Arrays.asList(PbeCryptoUtil.ALGORITHM_PBE_MD5_DES, PbeCryptoUtil.ALGORITHM_PBE_HMAC_AES)
                   .contains(algorithm))
            throw new NoSuchAlgorithmException("Unsupported algorithms");
        this.algorithm = algorithm;
    }

    @Override
    public String decrypt(String source, String key)
            throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            NoSuchPaddingException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt, iterationCount);
        SecretKey secretKey = SecretKeyFactory.getInstance(algorithm).generateSecret(keySpec);
        AlgorithmParameterSpec algParamSpec = new PBEParameterSpec(salt, iterationCount);
        Cipher dCipher = Cipher.getInstance(secretKey.getAlgorithm());
        dCipher.init(Cipher.DECRYPT_MODE, secretKey, algParamSpec);
        byte[] encArray = source.getBytes(StandardCharsets.UTF_8);
        byte[] decArray = dCipher.doFinal(encArray);
        return new String(decArray, StandardCharsets.UTF_8);
    }

    @Override
    public String encrypt(String source, String key)
            throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            NoSuchPaddingException,
            InvalidAlgorithmParameterException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException {
        KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt, iterationCount);
        SecretKey secretKey = SecretKeyFactory.getInstance(algorithm).generateSecret(keySpec);
        AlgorithmParameterSpec algParamSpec = new PBEParameterSpec(salt, iterationCount);

        Cipher eCipher = Cipher.getInstance(secretKey.getAlgorithm());
        eCipher.init(Cipher.ENCRYPT_MODE, secretKey, algParamSpec);
        byte[] decArray = source.getBytes(StandardCharsets.UTF_8);
        byte[] encArray = eCipher.doFinal(decArray);
        return new String(encArray, StandardCharsets.UTF_8);
    }
}
*/