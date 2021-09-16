package org.hsh.crm.ms.users.dto.convertor;

import org.hsh.common.dal.converter.EntityLoader;
import org.hsh.crm.ms.users.dal.entities.User;
import org.hsh.crm.ms.users.dal.entities.UserStateEnum;
import org.hsh.crm.ms.users.dto.UserDto;

import java.util.stream.Collectors;

public class UserConvertor {
    private UserConvertor() {
        // hide
    }

    public static UserDto toDto(User entity) {
        if(entity == null)
            return null;

        UserDto dto = new UserDto();

        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setPasswordEncrypted(entity.getPasswordEncrypted());
        dto.setPrivateKey(entity.getPrivateKey());
        dto.setPublicKey(entity.getPublicKey());
        dto.seteMail(entity.geteMail());
        dto.setCellPhone(entity.getCellPhone());
        dto.setPerson(entity.getPerson());
        dto.setState(entity.getState()
                           .toString());
        dto.setRoles(entity.getRoles()
                           .stream()
                           .map(RoleConvertor::toDto)
                           .collect(Collectors.toList()));
        return dto;
    }

    public static User toEntity(UserDto dto, EntityLoader<User> loader) {
        if(dto == null)
            return null;
        if(isNew(dto))
            return toNewEntity(dto);

        return toExistedEntity(dto, loader);
    }

    private static boolean isNew(UserDto dto) {
        return dto.getId() == null;
    }

    private static User toNewEntity(UserDto dto) {
        User entity = new User();

        return fillEntity(dto, entity);
    }

    private static User toExistedEntity(UserDto dto, EntityLoader<User> loader) {
        if(loader == null)
            return null;
        User entity = loader.getFilteredData(dto.getId());
        if(entity == null)
            return null;

        return fillEntity(dto, entity);
    }

    private static User fillEntity(UserDto dto, User entity) {
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setPasswordEncrypted(dto.getPasswordEncrypted());
        entity.setPrivateKey(dto.getPrivateKey());
        entity.setPublicKey(dto.getPublicKey());
        entity.seteMail(dto.geteMail());
        entity.setCellPhone(dto.getCellPhone());
        entity.setPerson(dto.getPerson());
        entity.setState(UserStateEnum.valueOf(dto.getState()));

        return entity;
    }
}
