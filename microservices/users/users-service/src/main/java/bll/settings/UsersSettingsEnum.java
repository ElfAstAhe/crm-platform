package bll.settings;

import common.bll.settings.Settings;

public enum UsersSettingsEnum implements Settings {
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
