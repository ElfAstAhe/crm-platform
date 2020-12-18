package bll.model.builder;

import bll.model.Audit;
import common.helpers.builder.BaseBuilder;

import java.time.OffsetDateTime;

public class AuditBuilder extends BaseBuilder<Audit> {
    private AuditBuilder() {
        super(Audit.class);
    }

    public AuditBuilder setId(Long id) {
        getInstance().setId(id);
        return this;
    }

    public AuditBuilder setEventDate(OffsetDateTime eventDate) {
        getInstance().setEventDate(eventDate);
        return this;
    }

    public AuditBuilder setEvent(String event) {
        getInstance().setEvent(event);
        return this;
    }

    public AuditBuilder setSource(String source) {
        getInstance().setSource(source);
        return this;
    }

    public AuditBuilder setRequestId(String requestId) {
        getInstance().setRequestId(requestId);
        return this;
    }

    public AuditBuilder setUser(String user) {
        getInstance().setUser(user);
        return this;
    }

    public AuditBuilder setStatus(String status) {
        getInstance().setStatus(status);
        return this;
    }

    public AuditBuilder setAdditional(String additional) {
        getInstance().setAdditional(additional);
        return this;
    }
}
