package common.bll.repository;

import common.bll.model.BusinessModel;
import common.bll.model.BusinessModelKey;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Интерфейс crud репозитория
 *
 * @param <Model> модель
 * @param <Key>   ключ
 * @author elf
 */
public interface CrudRepository
        <Model extends BusinessModel<? extends BusinessModelKey>,
                Key extends BusinessModelKey> {
    /**
     * Получить экземпляр по id
     *
     * @param id УИЭ
     * @return модель
     */
    Future<Model> findAsync(Object id);

    Model find(Object id);

    /**
     * Получить экземпляр по коду (уникальный ключ)
     *
     * @param key ключ
     * @return модель
     */
    Future<Model> findByKeyAsync(Key key);

    Model findByKey(Key key);

    /**
     * Создать экземпляр
     *
     * @param model модель
     * @return модель
     */
    Future<Model> createAsync(Model model);

    Model create(Model model);

    /**
     * Изменить экземпляр
     *
     * @param model модель
     * @return модель
     */
    Future<Model> editAsync(Model model);

    Model edit(Model model);

    /**
     * Физически удалить экземпляр
     *
     * @param id УИЭ
     */
    void removeAsync(Object id);

    void remove(Object id);

    /**
     * Получить список всех экземпляров
     *
     * @return модель
     */
    Future<List<Model>> listAllAsync();

    List<Model> listAll();
}
