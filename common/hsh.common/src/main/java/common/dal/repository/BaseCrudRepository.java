package common.dal.repository;

import common.bll.model.BusinessModel;
import common.bll.repository.CrudRepository;
import common.bll.repository.ListConditions;
import common.dal.converter.SimpleConverter;
import common.dal.dao.CrudDao;
import common.dal.entity.IdEntity;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Базовая реализация crud репозитория, все наследники должны быть ejb
 *
 * @param <TModel>          модель
 * @param <TKey>            ключ
 * @param <TId>             УИЭ
 * @param <TListConditions> условия отбора
 *
 * @author elf
 */
public abstract class BaseCrudRepository<
            TModel extends BusinessModel,
            TKey,
            TId,
            TListConditions extends ListConditions>
        implements CrudRepository<TModel, TKey, TId, TListConditions> {

    @Override
    public TModel find(TId id) {
        return getConverter().toModel(getCrudDao().find(id));
    }

    @Override
    @Asynchronous
    public Future<TModel> findAsync(TId id) {
//        return getExecutorService().submit(() -> this.find(id));
        return new AsyncResult<>(find(id));
    }

    @Override
    public TModel findByKey(TKey key) {
        return getConverter().toModel(getCrudDao().findByKey(toDaoKey(key)));
    }

    @Override
    public Future<TModel> findByKeyAsync(TKey key) {
        return new AsyncResult<>(findByKey(key));
    }

    @Override
    public TModel create(TModel model) {
        IdEntity entity = getConverter().toNewEntity(model);
        return getConverter().toModel(getCrudDao().create(entity));
    }

    @Override
    @Asynchronous
    public Future<TModel> createAsync(TModel model) {
//        return getExecutorService().submit(() -> this.create(model));
        return new AsyncResult<>(create(model));
    }

    @Override
    public TModel edit(TModel model) {
        // Валидируем
        // validator.validateExisted(model);
        // Подгружаем
        IdEntity entity = getCrudDao().find(model.getId());
        if (entity == null)
            return null;
        return getConverter().toModel(getCrudDao().edit(getConverter().toEntity(model, entity)));
    }

    @Override
    @Asynchronous
    public Future<TModel> editAsync(TModel model) {
//        return getExecutorService().submit(() -> this.edit(model));
        return new AsyncResult<>(edit(model));
    }

    @Override
    public void remove(TId id) {
        IdEntity entity = getCrudDao().find(id);
        if (entity != null)
            getCrudDao().remove(entity);
    }

    @Override
    @Asynchronous
    public void removeAsync(TId id) {
        remove(id);
    }

    @Override
    public List<TModel> listAll() {
        return getCrudDao()
                .listAll()
                .stream()
                .map((e) -> getConverter().toModel(e))
                .collect(Collectors.toList());
    }

    @Override
    @Asynchronous
    public Future<List<TModel>> listAllAsync() {
//        return getExecutorService().submit(() -> this.listAll());
        return new AsyncResult<>(listAll());
    }

    @Override
    public List<TModel> list(TListConditions conditions) {
        return getCrudDao()
                .list(toDaoListConditions(conditions))
                .stream()
                .map((e) -> getConverter().toModel(e))
                .collect(Collectors.toList());
    }

    @Override
    public Future<List<TModel>> listAsync(TListConditions conditions) {
//        return getExecutorService().submit(() -> this.list(conditions));
        return new AsyncResult<>(list(conditions));
    }

//    protected abstract ExecutorService getExecutorService();

    protected abstract <Entity extends IdEntity, Key> CrudDao<Entity, Key> getCrudDao();

    protected abstract <Entity extends IdEntity> SimpleConverter<Entity, TModel> getConverter();

    protected abstract common.dal.dao.ListConditions toDaoListConditions(TListConditions repoListConditions);

    protected abstract <Key> Key toDaoKey(TKey key);
}
