package common.dal.converter;

import common.bll.model.BusinessModel;
import common.dal.entity.IdEntity;

/**
 * Конвертер таблицы связи
 * Логическая связь 1 ко многим
 * Физическая связь многие ко многим
 *
 * @author elf
 * @param <Entity> сущность
 * @param <Model>  бизнес модель
 * @param <OwnerModel> тип ключа владельца связи
 */
public interface TwoLinksConverter <Entity extends IdEntity, Model extends BusinessModel, OwnerModel extends BusinessModel> {
    Entity toNewEntity(Model model, OwnerModel ownerModel);

    Model toModel(Entity entity);

//    Entity toEntity(Model model, Entity entity, OwnerModel ownerModel);
}
