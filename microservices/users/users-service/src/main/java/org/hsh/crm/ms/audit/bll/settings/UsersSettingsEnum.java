package org.hsh.crm.ms.audit.bll.settings;

import common.bll.settings.Settings;

public enum UsersSettingsEnum implements Settings {
    DUMMY("nothing", false),
    AUDIT_SERVICE("http://localhost:8080/audit", false);

    private final Object defaultValue;
    private final boolean encrypted;

    UsersSettingsEnum(Object defaultValue, boolean isEncrypted) {
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
