package common.bll.repository;

import common.bll.settings.Settings;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.concurrent.Future;

/**
 * Интерфейс репозитория настроек
 *
 * @Author elf
 *
 * @param <TSettings>
 */
public interface SettingsRepository<TSettings extends Settings> {

    String getStringValue(TSettings setting);

    String getStringValue(TSettings setting, String defaultValue);

    void setStringValue(TSettings setting, String value);

    Future<String> getStringValueAsync(TSettings setting);

    Future<String> getStringValueAsync(TSettings setting, String defaultValue);

    void setStringValueAsync(TSettings setting, String value);

    Integer getIntegerValue(TSettings setting);

    Integer getIntegerValue(TSettings setting, Integer defaultValue);

    void setIntegerValue(TSettings setting, Integer value);

    Future<Integer> getIntegerValueAsync(TSettings setting);

    Future<Integer> getIntegerValueAsync(TSettings setting, Integer defaultValue);

    void setIntegerValueAsync(TSettings setting, Integer value);

    OffsetDateTime getOffsetDateTimeValue(TSettings setting);

    OffsetDateTime getOffsetDateTimeValue(TSettings setting, OffsetDateTime defaultValue);

    void setOffsetDateTimeValue(TSettings setting, OffsetDateTime value);

    Future<OffsetDateTime> getOffsetDateTimeValueAsync(TSettings setting);

    Future<OffsetDateTime> getOffsetDateTimeValueAsync(TSettings setting, OffsetDateTime defaultValue);

    void setOffsetDateTimeValueAsync(TSettings setting, OffsetDateTime value);

    Boolean getBooleanValue(TSettings setting);

    Boolean getBooleanValue(TSettings setting, Boolean defaultValue);

    void setBooleanValue(TSettings setting, Boolean value);

    Future<Boolean> getBooleanValueAsync(TSettings setting);

    Future<Boolean> getBooleanValueAsync(TSettings setting, Boolean defaultValue);

    void setBooleanValueAsync(TSettings setting, Boolean value);

    BigDecimal getBigDecimalValue(TSettings setting);

    BigDecimal getBigDecimalValue(TSettings setting, BigDecimal defaultValue);

    void setBigDecimalValue(TSettings setting, BigDecimal value);

    Future<BigDecimal> getBigDecimalValueAsync(TSettings setting);

    Future<BigDecimal> getBigDecimalValueAsync(TSettings setting, BigDecimal defaultValue);

    void setBigDecimalValueAsync(TSettings setting, BigDecimal value);
}
