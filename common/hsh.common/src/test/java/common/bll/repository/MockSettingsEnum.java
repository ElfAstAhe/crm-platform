package common.bll.repository;

import common.bll.settings.Settings;

public enum MockSettingsEnum implements Settings {
    DUMMY1("Test", false),
    DUMMY2(12345, false);

    private final Object defaultValue;
    private final boolean encrypted;

    MockSettingsEnum(Object defaultValue, boolean isEncrypted) {
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
