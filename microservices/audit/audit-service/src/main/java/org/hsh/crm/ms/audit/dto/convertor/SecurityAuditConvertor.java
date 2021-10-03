package org.hsh.crm.ms.audit.dto.convertor;

import org.hsh.common.dal.converter.EntityLoader;
import org.hsh.crm.ms.audit.dal.entities.BaseSecurityAudit;
import org.hsh.crm.ms.audit.dto.AuditStatusEnum;
import org.hsh.crm.ms.audit.dto.SecurityAudit;
import org.hsh.crm.ms.audit.dto.SecurityAuditEventEnum;
import org.hsh.crm.ms.audit.dto.builders.SecurityAuditBuilder;

public class SecurityAuditConvertor {
    private SecurityAuditConvertor() {
        // hide constructor
    }

    public static SecurityAudit toDto(org.hsh.crm.ms.audit.bll.model.SecurityAudit model) {
        if (model == null)
            return null;

        return SecurityAuditBuilder.get()
                                   .setId(model.getId())
                                   .setDate(model.getEventDate())
                                   .setSource(model.getSource())
                                   .setRequestId(model.getRequestId())
                                   .setEvent(SecurityAuditEventEnum.valueOf(model.getEvent().toString()))
                                   .setUser(model.getUserLogin())
                                   .setStatus(AuditStatusEnum.valueOf(model.getStatus().toString()))
                                   .build();
    }

    public static org.hsh.crm.ms.audit.bll.model.SecurityAudit toModel(SecurityAudit dto) {
        if(dto == null)
            return null;
        org.hsh.crm.ms.audit.bll.model.SecurityAudit model = new org.hsh.crm.ms.audit.bll.model.SecurityAudit();
        model.setId(dto.getId());
        model.setEventDate(dto.getDate());
        model.setStatus(org.hsh.crm.ms.audit.bll.model.AuditStatusEnum.valueOf(dto.getStatus().toString()));
        model.setSource(dto.getSource());
        model.setRequestId(dto.getRequestId());
        model.setEvent(org.hsh.crm.ms.audit.bll.model.SecurityAuditEventEnum.valueOf(dto.getEvent().toString()));
        model.setUserLogin(dto.getUser());

        return model;
    }

/*
    public static BaseSecurityAudit toEntity(SecurityAudit dto, EntityLoader<BaseSecurityAudit> loader) {
        if (dto == null)
            return null;
        if (isNew(dto))
            return toNewEntity(dto);

        return toExistedEntity(dto, loader);
    }

    private static boolean isNew(SecurityAudit dto) {
        return dto.getId() == null;
    }

    private static BaseSecurityAudit toNewEntity(SecurityAudit dto) {
        BaseSecurityAudit entity = new BaseSecurityAudit();

        return fillEntity(entity, dto);
    }

    private static BaseSecurityAudit toExistedEntity(SecurityAudit dto, EntityLoader<BaseSecurityAudit> loader) {
        if (loader == null)
            return null;
        BaseSecurityAudit entity = loader.getFilteredData(dto.getId());
        if (entity == null)
            return null;

        return fillEntity(entity, dto);
    }

    private static BaseSecurityAudit fillEntity(BaseSecurityAudit entity, SecurityAudit dto) {
        entity.setEventDate(dto.getDate());
        entity.setSource(dto.getSource());
        entity.setRequestId(dto.getRequestId());
        entity.setEvent(org.hsh.crm.ms.audit.bll.model.SecurityAuditEventEnum.valueOf(dto.getEvent().toString()));
        entity.setUserLogin(dto.getUser());
        entity.setStatus(org.hsh.crm.ms.audit.bll.model.AuditStatusEnum.valueOf(dto.getStatus().toString()));
        return entity;
    }
*/
}
