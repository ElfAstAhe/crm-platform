package common.dal.dao;

import java.util.Map;

/**
 * dao filter params
 *
 * @author elf
 */
public interface FilterParams {
    int getFromRow();
    int getRowCount();
    Map<String,Object> getFilter();
    SortOrderEnum getSortOrder();
    String getField();
}
