package common.bll.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Future;

class BaseSettingsRepositoryTest {
    @Test
    public void getStringValue_valueExists_shouldReturnValue() {
        // prepare
        MockBaseSettingsRepository repo = new MockBaseSettingsRepository();
        repo.setReturnNulls(false);
        // act
        String actual = repo.getStringValue(MockSettingsEnum.DUMMY1);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(MockBaseSettingsRepository.EXPECTED_DATA_1, actual);
    }

    @Test
    public void getStringValue_valueNotExists_shouldReturnDefaultValue() {
        // prepare
        MockBaseSettingsRepository repo = new MockBaseSettingsRepository();
        repo.setReturnNulls(true);
        // act
        String actual = repo.getStringValue(MockSettingsEnum.DUMMY1);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(MockBaseSettingsRepository.EXPECTED_DEFAULT_DATA_1, actual);
    }

    @Test
    public void getStringValueAsync_shouldReturnValue() {
        // prepare
        MockBaseSettingsRepository repo = new MockBaseSettingsRepository();
        repo.setReturnNulls(false);
        // act
        Future<String> actual = repo.getStringValueAsync(MockSettingsEnum.DUMMY1);
        // assert
        Assertions.assertNotNull(actual);
    }

    @Test
    public void getStringValueAsync_shouldReturnValue2() {
        // prepare
        MockBaseSettingsRepository repo = new MockBaseSettingsRepository();
        repo.setReturnNulls(false);
        // act
        Future<String> actual = repo.getStringValueAsync(MockSettingsEnum.DUMMY1, null);
        // assert
        Assertions.assertNotNull(actual);
    }

    @Test
    public void setStringValue_useValue_shouldSetValue() {
        // prepare
        MockBaseSettingsRepository repo = new MockBaseSettingsRepository();
        String expected = "TestExpected";
        // act
        repo.setStringValue(MockSettingsEnum.DUMMY1, expected);
        String actual = repo.getStringValue(MockSettingsEnum.DUMMY1);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void setStringValue_useNull_shouldSetDefaultValue() {
        // prepare
        MockBaseSettingsRepository repo = new MockBaseSettingsRepository();
        // act
        repo.setStringValue(MockSettingsEnum.DUMMY1, null);
        String actual = repo.getStringValue(MockSettingsEnum.DUMMY1);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(MockSettingsEnum.DUMMY1.getDefaultValue(), actual);
    }
}