package common.dto.converter;

import common.dal.entity.IdEntity;

public interface EntityConverter<Dto, Entity extends IdEntity> {
    /**
     * преобразовать entity в dto
     * @param entity entity
     * @return dto
     */
    Dto toDto(Entity entity);

    /**
     * преобразовать dto в entity
     * @param dto dto
     * @return entity
     */
    Entity toEntity(Dto dto);
}
