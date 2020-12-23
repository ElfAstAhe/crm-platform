package common.util.cryptography;

import common.exceptions.CryptoUtilException;

public interface CryptoUtil extends Encryptor, Decryptor{
    String encrypt(String source, String key) throws CryptoUtilException;

    String decrypt(String sourceBase64, String key) throws CryptoUtilException;
}
