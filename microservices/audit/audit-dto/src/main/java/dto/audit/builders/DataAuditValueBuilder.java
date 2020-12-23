package dto.audit.builders;

import common.helpers.builder.base.BaseBuilder;
import dto.audit.DataAuditValue;

public class DataAuditValueBuilder extends BaseBuilder<DataAuditValue> {
    protected DataAuditValueBuilder() {
        super(DataAuditValue.class);
    }

    public static DataAuditValueBuilder get() {
        return new DataAuditValueBuilder();
    }

    public DataAuditValueBuilder setName(String name) {
        getInstance().setName(name);
        return this;
    }

    public DataAuditValueBuilder setDescription(String description) {
        getInstance().setDescription(description);
        return this;
    }

    public DataAuditValueBuilder setBefore(String before) {
        getInstance().setBefore(before);
        return this;
    }

    public DataAuditValueBuilder setAfter(String after) {
        getInstance().setAfter(after);
        return this;
    }
}
