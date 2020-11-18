package common.dal.dao;

public enum SortOrderEnum {
    UNSORTED(DaoUtils.SQL_ASC),
    ASCENDING(DaoUtils.SQL_ASC),
    DESCENDING(DaoUtils.SQL_DESC);

    private final String sql;

    SortOrderEnum(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
