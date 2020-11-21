package common.bll.repository;

import common.bll.settings.Settings;

public enum TestSettingsEnum implements Settings {
    DUMMY1("Test"),
    DUMMY2(12345);

    private final Object defaultValue;

    TestSettingsEnum(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }
}
