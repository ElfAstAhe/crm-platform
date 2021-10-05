package org.hsh.crm.ms.audit.bll.settings;

import org.hsh.common.bll.settings.Settings;
import org.hsh.crm.ms.audit.dal.dao.AuditDaoStrategyKeyEnum;

public enum AuditSettingsEnum implements Settings {
    DUMMY("nothing", false),
    HISTORY_MAX_MONTH(3, false),
    DATA_AUDIT_STRATEGY(AuditDaoStrategyKeyEnum.FIRST.toString(), false),
    SECURITY_AUDIT_STRATEGY(AuditDaoStrategyKeyEnum.FIRST.toString(), false),
    HISTORY_TAIL_DAYS(30, false);

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
