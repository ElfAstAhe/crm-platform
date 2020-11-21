package common.bll.repository;

import common.bll.settings.Settings;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Future;

/**
 * Базовая реализация репо настроек
 *
 * @param <TSettings>
 * @author elf
 */
public abstract class BaseSettingsRepository<TSettings extends Settings> implements SettingsRepository<TSettings> {

    // ============================================================
    // String type
    // ============================================================
    @Override
    public String getStringValue(TSettings setting) {
        return getStringValue(setting, (String) setting.getDefaultValue());
    }

    @Override
    public String getStringValue(TSettings setting, String defaultValue) {
        String value = getFromSource(setting);
        if (value == null)
            return defaultValue;
        return value;
    }

    @Override
    public void setStringValue(TSettings setting, String value) {
        setToSource(setting, value == null ? String.valueOf(setting.getDefaultValue()) : value);
    }

    @Override
    @Asynchronous
    public Future<String> getStringValueAsync(TSettings setting) {
        return new AsyncResult<>(getStringValue(setting));
    }

    @Override
    @Asynchronous
    public Future<String> getStringValueAsync(TSettings setting, String defaultValue) {
        return new AsyncResult<>(getStringValue(setting, defaultValue));
    }

    @Override
    @Asynchronous
    public void setStringValueAsync(TSettings setting, String value) {
        setStringValue(setting, value);
    }

    // ============================================================
    // Integer type
    // ============================================================
    @Override
    public Integer getIntegerValue(TSettings setting) {
        return getIntegerValue(setting, (Integer) setting.getDefaultValue());
    }

    @Override
    public Integer getIntegerValue(TSettings setting, Integer defaultValue) {
        return Integer.parseInt(getStringValue(setting, defaultValue.toString()));
    }

    @Override
    public void setIntegerValue(TSettings setting, Integer value) {
        setStringValue(setting, value == null ? null : value.toString());
    }

    @Override
    @Asynchronous
    public Future<Integer> getIntegerValueAsync(TSettings setting) {
        return new AsyncResult<>(getIntegerValue(setting));
    }

    @Override
    @Asynchronous
    public Future<Integer> getIntegerValueAsync(TSettings setting, Integer defaultValue) {
        return new AsyncResult<>(getIntegerValue(setting, defaultValue));
    }

    @Override
    @Asynchronous
    public void setIntegerValueAsync(TSettings settings, Integer value) {
        setIntegerValue(settings, value);
    }

    // ============================================================
    // OffsetDateTime type
    // ============================================================
    @Override
    public OffsetDateTime getOffsetDateTimeValue(TSettings setting) {
        return getOffsetDateTimeValue(setting, (OffsetDateTime) setting.getDefaultValue());
    }

    @Override
    public OffsetDateTime getOffsetDateTimeValue(TSettings setting, OffsetDateTime defaultValue) {
        return OffsetDateTime.parse(getStringValue(setting, defaultValue.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    @Override
    public void setOffsetDateTimeValue(TSettings setting, OffsetDateTime value) {
        setStringValue(setting, value == null ? null : value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }

    @Override
    @Asynchronous
    public Future<OffsetDateTime> getOffsetDateTimeValueAsync(TSettings setting) {
        return new AsyncResult<>(getOffsetDateTimeValue(setting));
    }

    @Override
    @Asynchronous
    public Future<OffsetDateTime> getOffsetDateTimeValueAsync(TSettings settings, OffsetDateTime defaultValue) {
        return new AsyncResult<>(getOffsetDateTimeValue(settings, defaultValue));
    }

    @Override
    @Asynchronous
    public void setOffsetDateTimeValueAsync(TSettings setting, OffsetDateTime value) {
        setOffsetDateTimeValue(setting, value);
    }

    // ============================================================
    // Boolean type
    // ============================================================
    @Override
    public Boolean getBooleanValue(TSettings setting) {
        return getBooleanValue(setting, (Boolean) setting.getDefaultValue());
    }

    @Override
    public Boolean getBooleanValue(TSettings setting, Boolean defaultValue) {
        return Boolean.parseBoolean(getStringValue(setting, defaultValue.toString()));
    }

    @Override
    public void setBooleanValue(TSettings setting, Boolean value) {
        setStringValue(setting, value == null ? null : value.toString());
    }

    @Override
    @Asynchronous
    public Future<Boolean> getBooleanValueAsync(TSettings setting) {
        return new AsyncResult<>(getBooleanValue(setting));
    }

    @Override
    @Asynchronous
    public Future<Boolean> getBooleanValueAsync(TSettings setting, Boolean defaultValue) {
        return new AsyncResult<>(getBooleanValue(setting, defaultValue));
    }

    @Override
    @Asynchronous
    public void setBooleanValueAsync(TSettings setting, Boolean value) {
        setBooleanValue(setting, value);
    }

    // ============================================================
    // BigDecimal type
    // ============================================================
    @Override
    public BigDecimal getBigDecimalValue(TSettings setting) {
        return getBigDecimalValue(setting, (BigDecimal) setting.getDefaultValue());
    }

    @Override
    public BigDecimal getBigDecimalValue(TSettings setting, BigDecimal defaultValue) {
        return new BigDecimal(getStringValue(setting, defaultValue.toString()));
    }

    @Override
    public void setBigDecimalValue(TSettings setting, BigDecimal value) {
        setStringValue(setting, value == null ? null : value.toString());
    }

    @Override
    @Asynchronous
    public Future<BigDecimal> getBigDecimalValueAsync(TSettings setting) {
        return new AsyncResult<>(getBigDecimalValue(setting));
    }

    @Override
    @Asynchronous
    public Future<BigDecimal> getBigDecimalValueAsync(TSettings setting, BigDecimal defaultValue) {
        return new AsyncResult<>(getBigDecimalValue(setting, defaultValue));
    }

    @Override
    @Asynchronous
    public void setBigDecimalValueAsync(TSettings setting, BigDecimal value) {
        setBigDecimalValue(setting, value);
    }

    protected abstract String getFromSource(TSettings setting);

    protected abstract void setToSource(TSettings setting, String value);
}
