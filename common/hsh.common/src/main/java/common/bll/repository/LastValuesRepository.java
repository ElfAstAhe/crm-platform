package common.bll.repository;

import common.bll.model.BusinessModel;

import java.util.List;
import java.util.concurrent.Future;

public interface LastValuesRepository<TKey,TModel extends BusinessModel> {
    /**
     * Получить список последних котировок по ключу
     *
     * @param key      ключ
     * @param rowCount кол-во строк
     * @return список моделей
     */
    public List<TModel> list(TKey key, int rowCount);

    /**
     * async, получить список последних котировок по ключу
     *
     * @param key      ключ
     * @param rowCount кол-во строк
     * @return список моделей
     */
    public Future<List<TModel>> listAsync(TKey key, int rowCount);
}
