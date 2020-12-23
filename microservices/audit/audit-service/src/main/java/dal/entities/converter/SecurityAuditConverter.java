package dal.entities.converter;

import bll.model.Audit;
import bll.model.builder.AuditBuilder;
import dal.entities.SecurityAudit;

public final class SecurityAuditConverter {
    private SecurityAuditConverter() {
        // hide
    }

    public static Audit toAudit(SecurityAudit entity) {
        return AuditBuilder.get()
                .setId(entity.getId())
                .setEventDate(entity.getEventDate())
                .setEvent(entity.getEvent().toString())
                .setSource(entity.getSource())
                .setRequestId(entity.getRequestId())
                .setUser(entity.getUserLogin())
                .setStatus(entity.getStatus().toString())
                .build();
    }
}
