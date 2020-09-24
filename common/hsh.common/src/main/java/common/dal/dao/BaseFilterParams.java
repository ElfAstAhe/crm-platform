package common.dal.dao;

import java.util.HashMap;
import java.util.Map;

public class BaseFilterParams implements FilterParams{
    public static final int DEFAULT_FROM_ROW = 0;
    public static final int DEFAULT_ROW_COUNT = 100;

    private int fromRow = DEFAULT_FROM_ROW;
    private int rowCount = DEFAULT_ROW_COUNT;
    private Map<String, Object> filter = new HashMap<>();
    private SortOrderEnum sortOrder = SortOrderEnum.UNSORTED;
    private String field;

    public BaseFilterParams() {
        // default constructor
    }

    public BaseFilterParams(int fromRow,
                            int rowCount,
                            Map<String, Object> filter,
                            SortOrderEnum sortOrder,
                            String field) {
        this.fromRow = fromRow;
        this.rowCount = rowCount;
        this.filter = filter;
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
    public Map<String, Object> getFilter() {
        return filter;
    }

    @Override
    public SortOrderEnum getSortOrder() {
        return sortOrder;
    }

    @Override
    public String getField() {
        return field;
    }

    public void setFromRow(int fromRow) {
        this.fromRow = fromRow;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }

    public void setSortOrder(SortOrderEnum sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setField(String field) {
        this.field = field;
    }
}
