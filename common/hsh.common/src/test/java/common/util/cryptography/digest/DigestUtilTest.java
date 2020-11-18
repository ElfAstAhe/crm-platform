package common.util.cryptography.digest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DigestUtilTest {
    private static final String DATA = "testData";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void encodeMD2_algorithmExists_shouldReturnEncryptData() {
        // prepare
        String expected = "gC4N/DkDHD//8jjWhhlgcg==";
        try {
            // act
            String actual = DigestUtil.encodeMD2(DATA);
            // assert
            Assertions.assertNotNull(actual);
            Assertions.assertEquals(expected, actual);
        } catch (NoSuchAlgorithmException ex) {
            Assertions.fail();
        }
    }

    @Test
    public void encodeMD5_algorithmExists_shouldReturnEncryptData() {
        // prepare
        String expected = "OnYPrnhNMKG1DjBOl6FzVQ==";
        try {
            // act
            String actual = DigestUtil.encodeMD5(DATA);
            // assert
            Assertions.assertNotNull(actual);
            Assertions.assertEquals(expected, actual);
        } catch (NoSuchAlgorithmException ex) {
            Assertions.fail();
        }
    }

    @Test
    public void encodeDigest_algorithmExists_shouldReturnEncodeData() {
        // prepare
        String expected = "OnYPrnhNMKG1DjBOl6FzVQ==";
        try {
            // act
            byte[] actual = DigestUtil.encodeDigest(DATA.getBytes(StandardCharsets.UTF_8), "MD5");
            String actualString = Base64.getEncoder()
                    .encodeToString(actual);
            // assert
            Assertions.assertNotNull(actual);
            Assertions.assertEquals(expected, actualString);
        } catch (NoSuchAlgorithmException ex) {
            Assertions.fail();
        }
    }

    @Test
    public void encodeDigest_algorithmNotExists_shouldThrowException() {
        // prepare
        // act
        // assert
        Assertions.assertThrows(NoSuchAlgorithmException.class, () -> DigestUtil.encodeDigest(DATA.getBytes(StandardCharsets.UTF_8), "MD6"));
    }
}