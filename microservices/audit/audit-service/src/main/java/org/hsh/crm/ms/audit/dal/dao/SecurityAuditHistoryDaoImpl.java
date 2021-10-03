package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.crm.ms.audit.dal.entities.SecurityAuditHistory;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class SecurityAuditHistoryDaoImpl extends BaseAsyncAuditDao<SecurityAuditHistory, Long> implements SecurityAuditHistoryDao {
    public SecurityAuditHistoryDaoImpl() {
        super(SecurityAuditHistory.class);
    }

    @Override
    public List<SecurityAuditHistory> listAll() {
        throw new UnsupportedOperationException("forbidden list all data");
    }
}
