package common.bll.provider;

import java.util.concurrent.Future;

/**
 * data provider layer
 *
 * @param <TResult>
 * @author elf
 */
public interface DataProvider<TResult> {
    TResult getData();

    Future<TResult> getDataAsync();
}
