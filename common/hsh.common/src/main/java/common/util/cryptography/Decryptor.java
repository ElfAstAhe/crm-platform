package common.util.cryptography;

import common.exceptions.CryptoUtilException;

public interface Decryptor {
    byte[] decrypt(byte[] source, byte[] key) throws CryptoUtilException;
}
