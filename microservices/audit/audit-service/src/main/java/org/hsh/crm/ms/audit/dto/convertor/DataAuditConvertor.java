package org.hsh.crm.ms.audit.dto.convertor;

import org.hsh.common.dal.converter.EntityLoader;
import org.hsh.crm.ms.audit.dal.entities.BaseDataAudit;
import org.hsh.crm.ms.audit.dal.entities.dto.DataAuditValues;
import org.hsh.crm.ms.audit.dto.AuditStatusEnum;
import org.hsh.crm.ms.audit.dto.DataAudit;
import org.hsh.crm.ms.audit.dto.DataAuditEventEnum;
import org.hsh.crm.ms.audit.dto.builders.DataAuditBuilder;
import org.hsh.ms.common.util.serialization.JsonSerializerHelper;

import java.util.Collections;

public class DataAuditConvertor {
    private DataAuditConvertor() {
        // hide constructor
    }

    public static DataAudit toDto(BaseDataAudit entity) {
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

    public static BaseDataAudit toEntity(DataAudit dto, EntityLoader<BaseDataAudit> loader) {
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

    private static BaseDataAudit toNewEntity(DataAudit dto) {
        BaseDataAudit entity = new BaseDataAudit();

        return fillEntity(entity, dto);
    }

    private static BaseDataAudit toExistedEntity(DataAudit dto, EntityLoader<BaseDataAudit> loader) {
        if (loader == null)
            return null;
        BaseDataAudit entity = loader.getFilteredData(dto.getId());
        if (entity == null)
            return null;
        return fillEntity(entity, dto);
    }

    private static BaseDataAudit fillEntity(BaseDataAudit entity, DataAudit dto) {
        entity.setEventDate(dto.getDate());
        entity.setSource(dto.getSource());
        entity.setRequestId(dto.getRequestId());
        entity.setEvent(org.hsh.crm.ms.audit.bll.model.DataAuditEventEnum.valueOf(dto.getEvent().toString()));
        entity.setClassName(dto.getClassName());
        entity.setClassDescription(dto.getClassDescription());
        entity.setObjectId(dto.getObjectId());
        entity.setObjectName(dto.getObjectName());
        entity.setValues(JsonSerializerHelper.serialize(new DataAuditValues(dto.getValues())));
        entity.setUserLogin(dto.getUser());
        entity.setRunAsUser(dto.getRunAsUser());
        entity.setStatus(org.hsh.crm.ms.audit.bll.model.AuditStatusEnum.valueOf(dto.getStatus().toString()));
        return entity;
    }
}
