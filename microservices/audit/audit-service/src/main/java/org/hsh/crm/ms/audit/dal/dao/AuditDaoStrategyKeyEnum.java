package org.hsh.crm.ms.audit.dal.dao;

public enum AuditDaoStrategyKeyEnum {
    FIRST,
    SECOND;

    private static final AuditDaoStrategyKeyEnum[] allValues = values();

    public AuditDaoStrategyKeyEnum nextKey() {
        return allValues[(ordinal() + 1) % allValues.length];
    }

    public AuditDaoStrategyKeyEnum prevKey() {
        return allValues[(ordinal() - 1 + allValues.length) % allValues.length];
    }
}
