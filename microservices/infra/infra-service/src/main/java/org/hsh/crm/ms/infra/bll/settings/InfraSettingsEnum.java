package org.hsh.crm.ms.infra.bll.settings;

import org.hsh.common.bll.settings.Settings;

public enum InfraSettingsEnum implements Settings {
    LOCATION_SERVICE("http://localhost:8080/fake-location", false);

    private final Object defaultValue;
    private final boolean encrypted;

    InfraSettingsEnum(Object defaultValue, boolean isEncrypted) {
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
