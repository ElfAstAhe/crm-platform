package common.bll.provider;

/**
 * data provider abstraction layer with filter
 *
 * @param <Filter> filter conditions
 * @param <Result> data result
 * @author elf
 */
public interface FilteredDataProvider<Filter, Result> {
    Result getFilteredData(Filter filter);
}
