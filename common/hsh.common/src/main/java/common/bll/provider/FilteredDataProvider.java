package common.bll.provider;

import java.util.concurrent.Future;

/**
 * data provider layer with filter
 *
 * @param <TResult>
 * @param <TFilter>
 * @author elf
 */
public interface FilteredDataProvider<TResult, TFilter> {
    TResult getFilteredData(TFilter filter);

    Future<TResult> getFilteredDataAsync(TFilter filter);
}
