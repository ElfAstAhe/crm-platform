package common.util.cryptography;

import common.exceptions.cryptography.CryptoUtilException;

public interface Decryptor {
    byte[] decrypt(byte[] source, byte[] key) throws CryptoUtilException;
}
