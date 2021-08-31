package org.hsh.crm.ms.audit.ep.dto.converter;

import common.dal.converter.EntityLoader;
import org.hsh.crm.ms.audit.dto.AuditStatusEnum;
import org.hsh.crm.ms.audit.dto.SecurityAudit;
import org.hsh.crm.ms.audit.dto.SecurityAuditEventEnum;
import org.hsh.crm.ms.audit.dto.builders.SecurityAuditBuilder;

public class SecurityAuditConverter {
    private SecurityAuditConverter() {
        // hide constructor
    }

    public static SecurityAudit toDto(org.hsh.crm.ms.audit.dal.entities.SecurityAudit entity) {
        if (entity == null)
            return null;

        return SecurityAuditBuilder.get()
                .setId(entity.getId())
                .setDate(entity.getEventDate())
                .setSource(entity.getSource())
                .setRequestId(entity.getRequestId())
                .setEvent(SecurityAuditEventEnum.valueOf(entity.getEvent().toString()))
                .setUser(entity.getUserLogin())
                .setStatus(AuditStatusEnum.valueOf(entity.getStatus().toString()))
                .build();
    }

    public static org.hsh.crm.ms.audit.dal.entities.SecurityAudit toEntity(SecurityAudit dto, EntityLoader<org.hsh.crm.ms.audit.dal.entities.SecurityAudit> loader) {
        if (dto == null)
            return null;
        if (isNew(dto))
            return toNewEntity(dto);

        return toExistedEntity(dto, loader);
    }

    private static boolean isNew(SecurityAudit dto) {
        return dto.getId() == null;
    }

    private static org.hsh.crm.ms.audit.dal.entities.SecurityAudit toNewEntity(SecurityAudit dto) {
        org.hsh.crm.ms.audit.dal.entities.SecurityAudit entity = new org.hsh.crm.ms.audit.dal.entities.SecurityAudit();

        return fillEntity(entity, dto);
    }

    private static org.hsh.crm.ms.audit.dal.entities.SecurityAudit toExistedEntity(SecurityAudit dto, EntityLoader<org.hsh.crm.ms.audit.dal.entities.SecurityAudit> loader) {
        if (loader == null)
            return null;
        org.hsh.crm.ms.audit.dal.entities.SecurityAudit entity = loader.getFilteredData(dto.getId());
        if (entity == null)
            return null;

        return fillEntity(entity, dto);
    }

    private static org.hsh.crm.ms.audit.dal.entities.SecurityAudit fillEntity(org.hsh.crm.ms.audit.dal.entities.SecurityAudit entity, SecurityAudit dto) {
        entity.setEventDate(dto.getDate());
        entity.setSource(dto.getSource());
        entity.setRequestId(dto.getRequestId());
        entity.setEvent(org.hsh.crm.ms.audit.dal.entities.SecurityAuditEventEnum.valueOf(dto.getEvent().toString()));
        entity.setUserLogin(dto.getUser());
        entity.setStatus(org.hsh.crm.ms.audit.dal.entities.AuditStatusEnum.valueOf(dto.getStatus().toString()));
        return entity;
    }
}
