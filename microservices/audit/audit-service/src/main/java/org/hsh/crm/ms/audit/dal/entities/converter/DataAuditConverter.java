package org.hsh.crm.ms.audit.dal.entities.converter;

import org.hsh.crm.ms.audit.bll.model.Audit;
import org.hsh.crm.ms.audit.bll.model.builder.AuditBuilder;
import org.hsh.crm.ms.audit.dal.entities.DataAudit;

import java.util.StringJoiner;

public final class DataAuditConverter {
    private DataAuditConverter() {
        // hide
    }

    public static Audit toAudit(DataAudit entity) {
        return AuditBuilder.get()
                .setId(entity.getId())
                .setEventDate(entity.getEventDate())
                .setEvent(entity.getEvent().toString())
                .setSource(entity.getSource())
                .setRequestId(entity.getRequestId())
                .setUser(entity.getUserLogin())
                .setAdditional(toAuditAdditional(entity))
                .setStatus(entity.getStatus().toString())
                .build();
    }

    private static String toAuditAdditional(DataAudit entity) {
        return new StringJoiner(",", "", "")
                .add("objectId=[" + entity.getObjectId() + "]")
                .add("objectName=[" + entity.getObjectName() + "]")
                .toString();
    }
}
