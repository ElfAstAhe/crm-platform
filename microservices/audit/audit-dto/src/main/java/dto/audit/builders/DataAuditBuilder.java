package dto.audit.builders;

import dto.audit.AuditStatusEnum;
import dto.audit.DataAudit;
import dto.audit.DataAuditEventEnum;
import dto.audit.DataAuditValue;
import org.hsh.common.helpers.builder.BaseBuilder;
import org.hsh.common.util.audit.AuditUtils;

import java.time.OffsetDateTime;
import java.util.List;

public class DataAuditBuilder extends BaseBuilder<DataAudit> {
    protected DataAuditBuilder() {
        super(DataAudit.class);
    }

    public static DataAuditBuilder get() {
        return new DataAuditBuilder();
    }

    public DataAuditBuilder setId(Long id) {
        getInstance().setId(id);
        return this;
    }

    public DataAuditBuilder setDate(OffsetDateTime date) {
        getInstance().setDate(date);
        return this;
    }

    public DataAuditBuilder setSource(String source) {
        getInstance().setSource(source);
        return this;
    }

    public DataAuditBuilder setRequestId(String requestId) {
        getInstance().setRequestId(requestId);
        return this;
    }

    public DataAuditBuilder setEvent(DataAuditEventEnum event) {
        getInstance().setEvent(event);
        return this;
    }

    public DataAuditBuilder setClassName(String className) {
        getInstance().setClassName(className);
        return this;
    }

    public DataAuditBuilder setClassDescription(String classDescription) {
        getInstance().setClassDescription(classDescription);
        return this;
    }

    public DataAuditBuilder setObjectId(String objectId) {
        getInstance().setObjectId(objectId);
        return this;
    }

    public DataAuditBuilder setObjectName(String objectName) {
        getInstance().setObjectName(objectName);
        return this;
    }

    public DataAuditBuilder setValues(List<DataAuditValue> values) {
        getInstance().setValues(values);
        return this;
    }

    public DataAuditBuilder setUser(String user) {
        getInstance().setUser(user);
        return this;
    }

    public DataAuditBuilder setRunAsUser(String runAsUser) {
        getInstance().setRunAsUser(runAsUser);
        return this;
    }

    public DataAuditBuilder setStatus(AuditStatusEnum status) {
        getInstance().setStatus(status);
        return this;
    }

    public DataAuditBuilder setCreatedInstance(Object created) {
        getInstance().setEvent(DataAuditEventEnum.CREATED);
        getInstance().setClassName(created.getClass().getName());
        getInstance().setClassName(AuditUtils.classDescription(created));
        getInstance().setObjectId(AuditUtils.objectIdValue(created));
        getInstance().setObjectName(AuditUtils.objectNameValue(created));
        getInstance().setValues(DataAuditValuesBuilder.get()
                .buildCreated(created));
        return this;
    }

    public DataAuditBuilder setModifiedInstance(Object before, Object after) {
        getInstance().setEvent(DataAuditEventEnum.CREATED);
        getInstance().setClassName(after.getClass().getName());
        getInstance().setClassName(AuditUtils.classDescription(after));
        getInstance().setObjectId(AuditUtils.objectIdValue(after));
        getInstance().setObjectName(AuditUtils.objectNameValue(after));
        getInstance().setValues(DataAuditValuesBuilder.get()
                .buildModified(before, after));
        return this;
    }

    public DataAuditBuilder setRemovedInstance(Object removed) {
        getInstance().setEvent(DataAuditEventEnum.CREATED);
        getInstance().setClassName(removed.getClass().getName());
        getInstance().setClassName(AuditUtils.classDescription(removed));
        getInstance().setObjectId(AuditUtils.objectIdValue(removed));
        getInstance().setObjectName(AuditUtils.objectNameValue(removed));
        getInstance().setValues(DataAuditValuesBuilder.get()
                .buildRemoved(removed));
        return this;
    }
}
