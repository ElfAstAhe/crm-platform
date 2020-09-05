package common.dal.dao;

/**
 * base list conditions class
 *
 * @author elf
 */
public class BaseListConditions implements ListConditions{

    private int fromRow;
    private int rowCount;

    public BaseListConditions(){
        fromRow = 0;
        rowCount = 0;
    }
    
    public BaseListConditions(int fromRow, int rowCount) {
        this.fromRow = fromRow;
        this.rowCount = rowCount;
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
    public void setFromRow(int fromRow) {
        this.fromRow = fromRow;
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
}
