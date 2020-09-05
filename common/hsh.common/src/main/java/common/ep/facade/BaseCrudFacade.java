package common.ep.facade;

import common.bll.model.BusinessModel;
import common.bll.repository.CrudRepository;
import common.bll.repository.ListConditions;
import common.dto.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author elf
 * @param <TDto>
 * @param <TKey>
 */
public abstract class BaseCrudFacade<TDto,TKey> {
    
    /**
     * Экземпляр по id
     * @param id id
     * @return dto
     */
    public TDto getInstance(Long id) {
        return getConverter().toDto(getCrudRepository().find(id));
    }
    
    /**
     * Экземпляр по уникальному ключу
     * @param key key
     * @return dto
     */
    public TDto getInstanceByKey(TKey key) {
        return getConverter().toDto(getCrudRepository().findByKey(toRepositoryKey(key)));
    }

    /**
     * Список всех
     * @return dto list
     */
    public List<TDto> listAllInstances(){
        return getCrudRepository().listAll()
                    .stream()
                    .map((c) -> getConverter().toDto(c))
                    .collect(Collectors.toList());
    }
    
    /**
     * Список по критерию
     * @param conditions condition list
     * @return dto list
     */
    public List<TDto> listInstances(Object... conditions){
        return getCrudRepository().list(toRepositoryListConditions(conditions))
                    .stream()
                    .map((c) -> getConverter().toDto(c))
                    .collect(Collectors.toList());
    }
    
    /**
     * Создание
     * @param dto dto
     * @return dto
     */
    public TDto createInstance(TDto dto) {
        // Получаем модель
        BusinessModel model = getConverter().toModel(dto);

        // Валидируем
        // validator.validateNew(model);

        return getConverter().toDto(getCrudRepository().create(model));
    }

    /**
     * Изменение
     * @param id id
     * @param dto dto
     * @return dto
     */
    public TDto editInstance(Long id, TDto dto) {
        // Получаем модель
        BusinessModel model = getConverter().toModel(dto);
            
        return getConverter().toDto(getCrudRepository().edit(model));
    }

    /**
     * Удаление
     * @param id id
     */
    public void removeInstance(Long id){
        getCrudRepository().remove(id);
    }

    protected abstract <TRepoKey,TListConditions extends ListConditions> CrudRepository<Model, TRepoKey, TListConditions> getCrudRepository();
    protected abstract <TModel extends BusinessModel> Converter<TDto,TModel> getConverter();
    protected abstract <TListConditions extends ListConditions> TListConditions toRepositoryListConditions(Object... conditions);
    protected abstract <Key> Key toRepositoryKey(TKey key);
}
