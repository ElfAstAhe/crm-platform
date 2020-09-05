package common.dal.repository;

import common.bll.model.BusinessModel;
import common.bll.repository.LastValuesRepository;

import java.util.List;
import java.util.concurrent.Future;

public class BaseLastValuesRepository<TKey, TModel extends BusinessModel> implements LastValuesRepository<TKey, TModel> {
    @Override
    public List<TModel> list(TKey tKey, int rowCount) {
        return null;
    }

    @Override
    public Future<List<TModel>> listAsync(TKey tKey, int rowCount) {
        return null;
    }
}
