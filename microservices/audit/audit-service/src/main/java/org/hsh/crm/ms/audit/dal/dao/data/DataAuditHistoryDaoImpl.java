package org.hsh.crm.ms.audit.dal.dao.data;

import org.hsh.crm.ms.audit.dal.dao.base.BaseAuditDao;
import org.hsh.crm.ms.audit.dal.entities.DataAuditHistory;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DataAuditHistoryDaoImpl extends BaseAuditDao<DataAuditHistory, Long> implements DataAuditHistoryDao {
    public DataAuditHistoryDaoImpl() {
        super(DataAuditHistory.class);
    }

    @Override
    public List<DataAuditHistory> listAll() {
        throw new UnsupportedOperationException("forbidden list all data");
    }
}
