package dto.audit.builders;

import dto.audit.AuditStatusEnum;
import dto.audit.SecurityAudit;
import dto.audit.SecurityAuditEventEnum;
import org.hsh.common.helpers.builder.BaseBuilder;

import java.time.OffsetDateTime;

public class SecurityAuditBuilder extends BaseBuilder<SecurityAudit> {
    protected SecurityAuditBuilder() {
        super(SecurityAudit.class);
    }

    public static SecurityAuditBuilder get() {
        return new SecurityAuditBuilder();
    }

    public SecurityAuditBuilder setId(Long id) {
        getInstance().setId(id);
        return this;
    }

    public SecurityAuditBuilder setDate(OffsetDateTime date) {
        getInstance().setDate(date);
        return this;
    }

    public SecurityAuditBuilder setSource(String source) {
        getInstance().setSource(source);
        return this;
    }

    public SecurityAuditBuilder setRequestId(String requestId) {
        getInstance().setRequestId(requestId);
        return this;
    }

    public SecurityAuditBuilder setEvent(SecurityAuditEventEnum event) {
        getInstance().setEvent(event);
        return this;
    }

    public SecurityAuditBuilder setUser(String user) {
        getInstance().setUser(user);
        return this;
    }

    public SecurityAuditBuilder setStatus(AuditStatusEnum status) {
        getInstance().setStatus(status);
        return this;
    }
}
