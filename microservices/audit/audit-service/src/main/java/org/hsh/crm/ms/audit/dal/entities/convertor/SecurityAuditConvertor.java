package org.hsh.crm.ms.audit.dal.entities.convertor;

import org.hsh.common.exceptions.runtime.base.DalRuntimeException;
import org.hsh.crm.ms.audit.bll.model.Audit;
import org.hsh.crm.ms.audit.bll.model.SecurityAudit;
import org.hsh.crm.ms.audit.bll.model.builder.AuditBuilder;
import org.hsh.crm.ms.audit.dal.entities.BaseSecurityAudit;

import java.lang.reflect.InvocationTargetException;

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

    public static SecurityAudit toModel(BaseSecurityAudit entity) {
        if(entity == null)
            return null;
        SecurityAudit model = new SecurityAudit();
        model.setId(entity.getId());
        model.setEventDate(entity.getEventDate());
        model.setStatus(entity.getStatus());
        model.setSource(entity.getSource());
        model.setRequestId(entity.getRequestId());
        model.setEvent(entity.getEvent());
        model.setUserLogin(entity.getUserLogin());
        return model;
    }

    public static BaseSecurityAudit toEntity(SecurityAudit model, Class<? extends BaseSecurityAudit> entityClass) {
        if(model == null || entityClass == null)
            return null;
        try {
            BaseSecurityAudit entity = entityClass.getConstructor().newInstance();
            entity.setId(model.getId());
            entity.setEventDate(model.getEventDate());
            entity.setStatus(model.getStatus());
            entity.setSource(model.getSource());
            entity.setRequestId(model.getRequestId());
            entity.setEvent(model.getEvent());
            entity.setUserLogin(model.getUserLogin());

            return entity;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException ex) {
            throw new DalRuntimeException("error instantiate security audit class", ex);
        }
    }
}
