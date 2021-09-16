package org.hsh.crm.ms.audit.dto.convertor;

import org.hsh.crm.ms.audit.dto.Audit;
import org.hsh.crm.ms.audit.dto.builders.AuditBuilder;

public class AuditConvertor {
    private AuditConvertor() {
        // hide
    }

    public static Audit toDto(org.hsh.crm.ms.audit.bll.model.Audit model) {
        return AuditBuilder.get()
                           .setId(model.getId())
                           .setEventDate(model.getEventDate())
                           .setEvent(model.getEvent())
                           .setSource(model.getSource())
                           .setRequestId(model.getRequestId())
                           .setUser(model.getUser())
                           .setStatus(model.getStatus())
                           .setAdditional(model.getAdditional())
                           .build();
    }
}
