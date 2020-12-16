package common.dal.converter;

import common.bll.model.BusinessModel;
import common.bll.model.BusinessModelKey;
import common.bll.provider.FilteredDataProvider;
import common.dal.entity.IdEntity;

/**
 * Конвертор entity <--> model
 *
 * @author elf
 * @param <Entity> сущность
 * @param <Model>  бизнес модель
 */
public interface SimpleConverter<Entity extends IdEntity,
        Model extends BusinessModel<? extends BusinessModelKey>>
{
    Entity toNewEntity(Model model);
    
    Model toModel(Entity entity);
    
    Entity toEntity(Model model, Entity entity);

    Entity toEntity(Model model, EntityLoader<Entity> loader);
}
