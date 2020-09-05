package common.dal.converter;

/**
 * Конвертер таблицы связи
 * Логическая связь 1 ко многим
 * Физическая связь многие ко многим
 *
 * @author elf
 * @param <Entity> тип результата entity
 * @param <Model> тип результата model
 * @param <OwnerModel> тип ключа владельца связи
 */
public interface TwoLinksConverter <Entity, Model, OwnerModel> {
    Entity toNewEntity(Model model, OwnerModel ownerModel);

    Model toModel(Entity entity);

//    Entity toEntity(Model model, Entity entity, OwnerModel ownerModel);
}
