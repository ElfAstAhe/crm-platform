package org.hsh.crm.ms.audit.dal.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuditDaoStrategyKeyEnumTest {
    @Test
    public void nextKey_firstElement_shouldReturnSecond() {
        // prepare
        AuditDaoStrategyKeyEnum expected = AuditDaoStrategyKeyEnum.SECOND;
        AuditDaoStrategyKeyEnum data = AuditDaoStrategyKeyEnum.FIRST;
        // act
        AuditDaoStrategyKeyEnum actual = data.nextKey();
        // assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void nextKey_lastElement_shouldReturnFirst() {
        // prepare
        AuditDaoStrategyKeyEnum expected = AuditDaoStrategyKeyEnum.FIRST;
        AuditDaoStrategyKeyEnum data = AuditDaoStrategyKeyEnum.SECOND;
        // act
        AuditDaoStrategyKeyEnum actual = data.nextKey();
        // assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void prevKey_firstElement_shouldReturnLast() {
        // prepare
        AuditDaoStrategyKeyEnum expected = AuditDaoStrategyKeyEnum.SECOND;
        AuditDaoStrategyKeyEnum data = AuditDaoStrategyKeyEnum.FIRST;
        // act
        AuditDaoStrategyKeyEnum actual = data.prevKey();
        // assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void prevKey_secondElement_shouldReturnFirst() {
        // prepare
        AuditDaoStrategyKeyEnum expected = AuditDaoStrategyKeyEnum.FIRST;
        AuditDaoStrategyKeyEnum data = AuditDaoStrategyKeyEnum.SECOND;
        // act
        AuditDaoStrategyKeyEnum actual = data.prevKey();
        // assert
        Assertions.assertEquals(expected, actual);
    }
}