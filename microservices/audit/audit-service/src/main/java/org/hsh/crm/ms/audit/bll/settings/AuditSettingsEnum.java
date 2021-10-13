package org.hsh.crm.ms.audit.bll.settings;

import org.hsh.common.bll.settings.Settings;
import org.hsh.crm.ms.audit.dal.dao.AuditDaoStrategyKeyEnum;

public enum AuditSettingsEnum implements Settings {
    MAX_DAYS_CURRENT(30, false),
    HISTORY_TAIL_DAYS(90, false),
    DATA_AUDIT_STRATEGY(AuditDaoStrategyKeyEnum.FIRST.toString(), false),
    SECURITY_AUDIT_STRATEGY(AuditDaoStrategyKeyEnum.FIRST.toString(), false),
    STRATEGY_SWITCH_START_DELAY_MILLIS(5000L, false),        // 5 секунд
    STRATEGY_SWITCH_INTERVAL_MILLIS(3600000L, false),        // 1 час
    HISTORY_TAIL_GROWER_EXECUTING(false, false),
    HISTORY_TAIL_GROWER_START_DELAY_MILLIS(5000L, false),    // 5 секунд
    HISTORY_TAIL_GROWER_INTERVAL_MILLIS(7200000L, false),    // 2 часа
    HISTORY_TAIL_CUTTER_EXECUTING(false, false),
    HISTORY_TAIL_CUTTER_START_DELAY_MILLIS(5000L, false),    // 5 секунд
    HISTORY_TAIL_CUTTER_INTERVAL_MILLIS(7500000L, false);    // 2 часа 5 минут

    private final Object defaultValue;
    private final boolean encrypted;

    AuditSettingsEnum(Object defaultValue, boolean isEncrypted) {
        this.defaultValue = defaultValue;
        this.encrypted = isEncrypted;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public boolean isEncrypted() {
        return encrypted;
    }
}
