/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.bll.repository;

/**
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

    public void setFromRow(int fromRow) {
        this.fromRow = fromRow;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
}
