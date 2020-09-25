package common.dal.dao;

import java.util.List;
import java.util.Map;

/**
 * dao filter params
 *
 * @author elf
 */
public interface FilterParams {
    int getFromRow();
    int getRowCount();
    List<Map<String, Object>> getFilterSet();
    SortOrderEnum getSortOrder();
    String getField();
}
