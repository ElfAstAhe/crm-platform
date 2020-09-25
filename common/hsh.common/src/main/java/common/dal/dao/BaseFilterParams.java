package common.dal.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseFilterParams implements FilterParams{
    public static final int DEFAULT_FROM_ROW = 0;
    public static final int DEFAULT_ROW_COUNT = 100;

    private final int fromRow;
    private final int rowCount;
    private final List<Map<String, Object>> filterSet;
    private final SortOrderEnum sortOrder;
    private final String field;

    public BaseFilterParams(int fromRow,
                            int rowCount,
                            List<Map<String, Object>> filterSet,
                            SortOrderEnum sortOrder,
                            String field) {
        this.fromRow = fromRow;
        this.rowCount = rowCount;
        this.filterSet = filterSet;
        this.sortOrder = sortOrder;
        this.field = field;
    }

    @Override
    public int getFromRow() {
        return fromRow;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public SortOrderEnum getSortOrder() {
        return sortOrder;
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public List<Map<String, Object>> getFilterSet() {
        return filterSet;
    }
}
