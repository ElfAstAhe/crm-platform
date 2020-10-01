package bll.settings;

import common.bll.settings.Settings;

public enum AppSettingsEnum implements Settings {
    DUMMY("nothing");

    private final Object defaultValue;

    AppSettingsEnum(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Object getDefaultValue() {
        return null;
    }
}
