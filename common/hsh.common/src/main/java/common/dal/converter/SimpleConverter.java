package common.dal.converter;

import common.bll.model.BusinessModel;
import common.dal.entity.Identity;

/**
 * Конвертор entity <--> model
 *
 * @author elf
 * @param <Entity>
 * @param <Key>
 * @param <Model>
 */
public interface SimpleConverter<Entity extends Identity, Key, Model extends BusinessModel<Key>> {
    Entity toNewEntity(Model model);
    
    Model toModel(Entity entity);
    
    Entity toEntity(Model model, Entity entity);

    Entity toEntity(Model model, EntityLoaderFunc<Entity> loader);
}
