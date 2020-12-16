package ep.dto.converter;

import common.dal.converter.EntityLoader;
import common.util.serialization.JsonSerializerHelper;
import dal.entities.dto.DataAuditValues;
import dto.audit.AuditStatusEnum;
import dto.audit.DataAudit;
import dto.audit.DataAuditEventEnum;
import dto.audit.builders.DataAuditBuilder;

import java.util.Collections;

public class DataAuditConverter{
    private DataAuditConverter() {
        // hide constructor
    }

    public static DataAudit toDto(dal.entities.DataAudit entity) {
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
                .setUser(entity.getUser())
                .setRunAsUser(entity.getRunAsUser())
                .setStatus(AuditStatusEnum.valueOf(entity.getStatus().toString()))
                .build();
    }

    public static dal.entities.DataAudit toEntity(DataAudit dto, EntityLoader<dal.entities.DataAudit> loader) {
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

    private static dal.entities.DataAudit toNewEntity(DataAudit dto) {
        dal.entities.DataAudit entity = new dal.entities.DataAudit();

        return fillEntity(entity, dto);
    }

    private static dal.entities.DataAudit toExistedEntity(DataAudit dto, EntityLoader<dal.entities.DataAudit> loader) {
        if (loader == null)
            return null;
        dal.entities.DataAudit entity = loader.getFilteredData(dto.getId());
        if (entity == null)
            return null;
        return fillEntity(entity, dto);
    }

    private static dal.entities.DataAudit fillEntity(dal.entities.DataAudit entity, DataAudit dto) {
        entity.setEventDate(dto.getDate());
        entity.setSource(dto.getSource());
        entity.setRequestId(dto.getRequestId());
        entity.setEvent(dal.entities.DataAuditEventEnum.valueOf(dto.getEvent().toString()));
        entity.setClassName(dto.getClassName());
        entity.setClassDescription(dto.getClassDescription());
        entity.setObjectId(dto.getObjectId());
        entity.setObjectName(dto.getObjectName());
        entity.setValues(JsonSerializerHelper.serialize(new DataAuditValues(dto.getValues())));
        entity.setUser(dto.getUser());
        entity.setRunAsUser(dto.getRunAsUser());
        entity.setStatus(dal.entities.AuditStatusEnum.valueOf(dto.getStatus().toString()));
        return entity;
    }
}
