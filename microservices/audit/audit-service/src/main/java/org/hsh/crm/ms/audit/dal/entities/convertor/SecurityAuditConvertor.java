package org.hsh.crm.ms.audit.dal.entities.convertor;

import org.hsh.crm.ms.audit.bll.model.Audit;
import org.hsh.crm.ms.audit.bll.model.builder.AuditBuilder;
import org.hsh.crm.ms.audit.dal.entities.SecurityAudit;

public final class SecurityAuditConvertor {
    private SecurityAuditConvertor() {
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
