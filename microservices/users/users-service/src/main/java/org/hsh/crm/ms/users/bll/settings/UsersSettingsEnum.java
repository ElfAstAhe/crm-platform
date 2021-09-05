package org.hsh.crm.ms.users.bll.settings;

import org.hsh.common.bll.settings.Settings;
import org.hsh.ms.common.ep.client.BaseRestClient;

public enum UsersSettingsEnum implements Settings {
    DUMMY("nothing", false),
    AUDIT_SERVICE("http://localhost:8080/audit", false),
    AUDIT_CONNECTION_TIME_OUT(BaseRestClient.DEFAULT_CONNECT_TIMEOUT_MILLISECONDS, false),
    AUDIT_READ_TIME_OUT(BaseRestClient.DEFAULT_READ_TIMEOUT_MILLISECONDS, false);

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
