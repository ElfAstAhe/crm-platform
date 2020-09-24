package common.dal.converter;

import common.bll.model.BusinessModel;
import common.dal.entity.IdEntity;
import common.dal.entity.LongIdentity;

/**
 * Конвертор entity <--> model
 *
 * @author elf
 * @param <Entity> сущность
 * @param <Model>  бизнес модель
 */
public interface SimpleConverter<Entity extends IdEntity,
        Model extends BusinessModel>
{
    Entity toNewEntity(Model model);
    
    Model toModel(Entity entity);
    
    Entity toEntity(Model model, Entity entity);

    Entity toEntity(Model model, EntityLoaderFunc<Entity> loader);
}
