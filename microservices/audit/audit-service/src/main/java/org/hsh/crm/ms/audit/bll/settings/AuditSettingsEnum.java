package org.hsh.crm.ms.audit.bll.settings;

import org.hsh.common.bll.settings.Settings;

public enum AuditSettingsEnum implements Settings {
    DUMMY("nothing", false),
    HISTORY_MAX_MONTH(3, false),
    DATA_AUDIT_STRATEGY(true, false),
    SECURITY_AUDIT_STRATEGY(true, false);

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
