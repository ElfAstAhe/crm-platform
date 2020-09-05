package common.util.cryptography;

import common.exceptions.cryptography.CryptoUtilException;

public interface Encryptor {
    byte[] encrypt(byte[] source, byte[] key) throws CryptoUtilException;
}
