package org.hsh.crm.ms.users.dto.convertor;

import org.hsh.common.dal.converter.EntityLoader;
import org.hsh.crm.ms.users.dal.entities.Role;
import org.hsh.crm.ms.users.dto.RoleDto;

public class RoleConvertor {
    private RoleConvertor() {
        // hide
    }

    public static RoleDto toDto(Role entity) {
        if(entity == null)
            return null;

        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    public static Role toEntity(RoleDto dto, EntityLoader<Role> loader) {
        if(dto == null)
            return null;
        if(isNew(dto))
            return toNewEntity(dto);

        return toExistedEntity(dto, loader);
    }

    private static boolean isNew(RoleDto dto) {
        return dto.getId() == null;
    }

    private static Role toNewEntity(RoleDto dto) {
        Role entity = new Role();

        return fillEntity(dto, entity);
    }

    private static Role toExistedEntity(RoleDto dto, EntityLoader<Role> loader) {
        if(loader == null)
            return null;
        Role entity = loader.getFilteredData(dto.getId());
        if(entity == null)
            return null;
        return fillEntity(dto, entity);
    }

    private static Role fillEntity(RoleDto dto, Role entity) {
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}
