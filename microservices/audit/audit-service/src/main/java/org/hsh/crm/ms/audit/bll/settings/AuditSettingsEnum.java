package org.hsh.crm.ms.audit.bll.settings;

import org.hsh.common.bll.settings.Settings;
import org.hsh.crm.ms.audit.dal.dao.AuditDaoStrategyKeyEnum;

public enum AuditSettingsEnum implements Settings {
    MAX_DAYS_CURRENT(30, false),
    HISTORY_TAIL_MONTH(3, false),
    DATA_AUDIT_STRATEGY(AuditDaoStrategyKeyEnum.FIRST.toString(), false),
    SECURITY_AUDIT_STRATEGY(AuditDaoStrategyKeyEnum.FIRST.toString(), false);

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
