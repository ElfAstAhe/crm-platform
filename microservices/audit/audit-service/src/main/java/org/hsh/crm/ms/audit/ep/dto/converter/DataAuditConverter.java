package org.hsh.crm.ms.audit.ep.dto.converter;

import org.hsh.common.dal.converter.EntityLoader;
import org.hsh.crm.ms.audit.dal.entities.dto.DataAuditValues;
import org.hsh.crm.ms.audit.dto.AuditStatusEnum;
import org.hsh.crm.ms.audit.dto.DataAudit;
import org.hsh.crm.ms.audit.dto.DataAuditEventEnum;
import org.hsh.crm.ms.audit.dto.builders.DataAuditBuilder;
import org.hsh.ms.common.util.serialization.JsonSerializerHelper;

import java.util.Collections;

public class DataAuditConverter{
    private DataAuditConverter() {
        // hide constructor
    }

    public static DataAudit toDto(org.hsh.crm.ms.audit.dal.entities.DataAudit entity) {
        if (entity == null)
            return null;
        DataAuditValues values = JsonSerializerHelper.deserialize(entity.getValues(), DataAuditValues.class);

        return DataAuditBuilder.get()
                               .setId(entity.getId())
                               .setDate(entity.getEventDate())
                               .setSource(entity.getSource())
                               .setRequestId(entity.getRequestId())
                               .setEvent(DataAuditEventEnum.valueOf(entity.getEvent().toString()))
                               .setClassName(entity.getClassName())
                               .setClassDescription(entity.getClassDescription())
                               .setObjectId(entity.getObjectId())
                               .setObjectName(entity.getObjectName())
                               .setValues(values != null ? values.getValues() : Collections.emptyList())
                               .setUser(entity.getUserLogin())
                               .setRunAsUser(entity.getRunAsUser())
                               .setStatus(AuditStatusEnum.valueOf(entity.getStatus().toString()))
                               .build();
    }

    public static org.hsh.crm.ms.audit.dal.entities.DataAudit toEntity(DataAudit dto, EntityLoader<org.hsh.crm.ms.audit.dal.entities.DataAudit> loader) {
        if (dto == null)
            return null;
        if (isNew(dto)) {
            return toNewEntity(dto);
        }

        return toExistedEntity(dto, loader);
    }

    private static boolean isNew(DataAudit dto) {
        return dto.getId() == null;
    }

    private static org.hsh.crm.ms.audit.dal.entities.DataAudit toNewEntity(DataAudit dto) {
        org.hsh.crm.ms.audit.dal.entities.DataAudit entity = new org.hsh.crm.ms.audit.dal.entities.DataAudit();

        return fillEntity(entity, dto);
    }

    private static org.hsh.crm.ms.audit.dal.entities.DataAudit toExistedEntity(DataAudit dto, EntityLoader<org.hsh.crm.ms.audit.dal.entities.DataAudit> loader) {
        if (loader == null)
            return null;
        org.hsh.crm.ms.audit.dal.entities.DataAudit entity = loader.getFilteredData(dto.getId());
        if (entity == null)
            return null;
        return fillEntity(entity, dto);
    }

    private static org.hsh.crm.ms.audit.dal.entities.DataAudit fillEntity(org.hsh.crm.ms.audit.dal.entities.DataAudit entity, DataAudit dto) {
        entity.setEventDate(dto.getDate());
        entity.setSource(dto.getSource());
        entity.setRequestId(dto.getRequestId());
        entity.setEvent(org.hsh.crm.ms.audit.dal.entities.DataAuditEventEnum.valueOf(dto.getEvent().toString()));
        entity.setClassName(dto.getClassName());
        entity.setClassDescription(dto.getClassDescription());
        entity.setObjectId(dto.getObjectId());
        entity.setObjectName(dto.getObjectName());
        entity.setValues(JsonSerializerHelper.serialize(new DataAuditValues(dto.getValues())));
        entity.setUserLogin(dto.getUser());
        entity.setRunAsUser(dto.getRunAsUser());
        entity.setStatus(org.hsh.crm.ms.audit.dal.entities.AuditStatusEnum.valueOf(dto.getStatus().toString()));
        return entity;
    }
}
