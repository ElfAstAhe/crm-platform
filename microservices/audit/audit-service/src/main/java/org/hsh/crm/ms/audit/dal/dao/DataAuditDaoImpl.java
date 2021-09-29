package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.crm.ms.audit.dal.entities.DataAudit;
import org.hsh.crm.ms.audit.dal.entities.DataAudit1;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class DataAuditDaoImpl extends BaseAuditDao<DataAudit, Long> implements DataAuditDao {
    public DataAuditDaoImpl() {
        super(DataAudit1.class);
    }
}
