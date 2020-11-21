package common.bll.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Future;

class BaseSettingsRepositoryTest {
    @Test
    public void getStringValue_valueExists_shouldReturnValue() {
        // prepare
        TestBaseSettingsRepository repo = new TestBaseSettingsRepository();
        repo.setReturnNulls(false);
        // act
        String actual = repo.getStringValue(TestSettingsEnum.DUMMY1);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(TestBaseSettingsRepository.EXPECTED_DATA_1, actual);
    }

    @Test
    public void getStringValue_valueNotExists_shouldReturnDefaultValue() {
        // prepare
        TestBaseSettingsRepository repo = new TestBaseSettingsRepository();
        repo.setReturnNulls(true);
        // act
        String actual = repo.getStringValue(TestSettingsEnum.DUMMY1);
        // assert
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(TestBaseSettingsRepository.EXPECTED_DEFAULT_DATA_1, actual);
    }

    @Test
    public void getStringValueAsync_shouldReturnValue() {
        // prepare
        TestBaseSettingsRepository repo = new TestBaseSettingsRepository();
        repo.setReturnNulls(false);
        // act
        Future<String> actual = repo.getStringValueAsync(TestSettingsEnum.DUMMY1);
        // assert
        Assertions.assertNotNull(actual);
    }
}