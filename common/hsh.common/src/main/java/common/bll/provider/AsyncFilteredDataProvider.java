package common.bll.provider;

import java.util.concurrent.Future;

/**
 * async data provider abstraction layer with filter condition
 * @param <Filter>
 * @param <Result>
 */
public interface AsyncFilteredDataProvider<Filter, Result> {
    Future<Result> getFilteredDataAsync(Filter filter);
}
