package common.bll.provider;

import java.util.concurrent.Future;

/**
 * data provider abstraction layer
 *
 * @param <Result> data result
 * @author elf
 */
public interface DataProvider<Result> {
    Result getData();
}
