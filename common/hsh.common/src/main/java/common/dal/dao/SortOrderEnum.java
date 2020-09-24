package common.dal.dao;

public enum SortOrderEnum {
    UNSORTED("asc"),
    ASCENDING("asc"),
    DESCENDING("desc");

    private final String sql;

    SortOrderEnum(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
