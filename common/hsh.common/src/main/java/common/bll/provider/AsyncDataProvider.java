package common.bll.provider;

import java.util.concurrent.Future;

/**
 * async data provider abstraction layer
 * @param <Result>
 */
public interface AsyncDataProvider<Result> {
    Future<Result> getDataAsync();
}
