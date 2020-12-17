package ep.dto.converter;

import common.dal.converter.EntityLoader;
import dto.audit.AuditStatusEnum;
import dto.audit.SecurityAudit;
import dto.audit.SecurityAuditEventEnum;
import dto.audit.builders.SecurityAuditBuilder;

public class SecurityAuditConverter {
    private SecurityAuditConverter() {
        // hide constructor
    }

    public static SecurityAudit toDto(dal.entities.SecurityAudit entity) {
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

    public static dal.entities.SecurityAudit toEntity(SecurityAudit dto, EntityLoader<dal.entities.SecurityAudit> loader) {
        if (dto == null)
            return null;
        if (isNew(dto))
            return toNewEntity(dto);

        return toExistedEntity(dto, loader);
    }

    private static boolean isNew(SecurityAudit dto) {
        return dto.getId() == null;
    }

    private static dal.entities.SecurityAudit toNewEntity(SecurityAudit dto) {
        dal.entities.SecurityAudit entity = new dal.entities.SecurityAudit();

        return fillEntity(entity, dto);
    }

    private static dal.entities.SecurityAudit toExistedEntity(SecurityAudit dto, EntityLoader<dal.entities.SecurityAudit> loader) {
        if (loader == null)
            return null;
        dal.entities.SecurityAudit entity = loader.getFilteredData(dto.getId());
        if (entity == null)
            return null;

        return fillEntity(entity, dto);
    }

    private static dal.entities.SecurityAudit fillEntity(dal.entities.SecurityAudit entity, SecurityAudit dto) {
        entity.setEventDate(dto.getDate());
        entity.setSource(dto.getSource());
        entity.setRequestId(dto.getRequestId());
        entity.setEvent(dal.entities.SecurityAuditEventEnum.valueOf(dto.getEvent().toString()));
        entity.setUserLogin(dto.getUser());
        entity.setStatus(dal.entities.AuditStatusEnum.valueOf(dto.getStatus().toString()));
        return entity;
    }
}
