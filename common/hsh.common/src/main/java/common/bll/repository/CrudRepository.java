package common.bll.repository;

import common.bll.model.BusinessModel;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Интерфейс crud репозитория
 * @author elf
 * @param <TModel> модель
 * @param <TKey> ключ
 * @param <TListConditions> условия отбора
 */
public interface CrudRepository<
        TModel extends BusinessModel,
        TKey,
        TListConditions extends ListConditions> {
    /**
     * Получить экземпляр по id
     * @param id УИЭ
     * @return модель
     */
    Future<TModel> findAsync(TId id);
    TModel find(TId id);
    
    /**
     * Получить экземпляр по коду (уникальный ключ)
     * @param key ключ
     * @return модель
     */
    Future<TModel> findByKeyAsync(TKey key);
    TModel findByKey(TKey key);

    /**
     * Создать экземпляр
     * @param model модель
     * @return модель
     */
    Future<TModel> createAsync(TModel model);
    TModel create(TModel model);
    
    /**
     * Изменить экземпляр
     * @param model модель
     * @return модель
     */
    Future<TModel> editAsync(TModel model);
    TModel edit(TModel model);
    
    /**
     * Физически удалить экземпляр
     * @param id УИЭ
     * @return 
     */
    void removeAsync(TId id);
    void remove(TId id);
    
    /**
     * Получить список всех экземпляров
     * @return 
     */
    Future<List<TModel>> listAllAsync();
    List<TModel> listAll();

    /**
     * Получить список экземпляров по критерию
     * @param conditions
     * @return 
     */
    Future<List<TModel>> listAsync(TListConditions conditions);
    List<TModel> list(TListConditions conditions);
}
