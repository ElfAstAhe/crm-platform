package org.hsh.crm.ms.audit.dal.entities.convertor;

import org.hsh.common.exceptions.runtime.base.DalRuntimeException;
import org.hsh.crm.ms.audit.bll.model.Audit;
import org.hsh.crm.ms.audit.bll.model.DataAudit;
import org.hsh.crm.ms.audit.bll.model.builder.AuditBuilder;
import org.hsh.crm.ms.audit.dal.entities.BaseDataAudit;

import java.lang.reflect.InvocationTargetException;
import java.util.StringJoiner;

public final class DataAuditConvertor {
    private DataAuditConvertor() {
        // hide
    }

    public static Audit toAudit(BaseDataAudit entity) {
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

    public static DataAudit toModel(BaseDataAudit entity) {
        if(entity == null)
            return null;
        DataAudit model = new DataAudit();
        model.setId(entity.getId());
        model.setEventDate(entity.getEventDate());
        model.setStatus(entity.getStatus());
        model.setSource(entity.getSource());
        model.setRequestId(entity.getRequestId());
        model.setEvent(entity.getEvent());
        model.setClassName(entity.getClassName());
        model.setClassDescription(entity.getClassDescription());
        model.setObjectId(entity.getObjectId());
        model.setObjectName(entity.getObjectName());
        model.setValues(entity.getValues());
        model.setUserLogin(entity.getUserLogin());
        model.setRunAsUser(entity.getRunAsUser());
        return model;
    }

    public static BaseDataAudit toEntity(DataAudit model, Class<? extends BaseDataAudit> entityClass) {
        if(model == null || entityClass == null)
            return null;
        try {
            BaseDataAudit entity = entityClass.getConstructor().newInstance();
            entity.setId(model.getId());
            entity.setEventDate(model.getEventDate());
            entity.setStatus(model.getStatus());
            entity.setSource(model.getSource());
            entity.setRequestId(model.getRequestId());
            entity.setEvent(model.getEvent());
            entity.setClassName(model.getClassName());
            entity.setClassDescription(model.getClassDescription());
            entity.setObjectId(model.getObjectId());
            entity.setObjectName(model.getObjectName());
            entity.setValues(model.getValues());
            entity.setUserLogin(model.getUserLogin());
            entity.setRunAsUser(model.getRunAsUser());
            return entity;
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new DalRuntimeException("error instantiate data audit class", ex);
        }
    }

    private static String toAuditAdditional(BaseDataAudit entity) {
        return new StringJoiner(",", "", "")
                .add("objectId=[" + entity.getObjectId() + "]")
                .add("objectName=[" + entity.getObjectName() + "]")
                .toString();
    }
}
