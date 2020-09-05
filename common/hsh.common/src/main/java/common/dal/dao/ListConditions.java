package common.dal.dao;

/**
 * Параметры поиска ListAsync для dao
 * @author elf
 */
public interface ListConditions {
    int getFromRow();
    int getRowCount();
    void setFromRow(int fromRow);
    void setRowCount(int rowCount);
}
