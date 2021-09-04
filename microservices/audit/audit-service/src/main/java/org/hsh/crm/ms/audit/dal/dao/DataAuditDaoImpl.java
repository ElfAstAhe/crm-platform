package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.crm.ms.audit.dal.entities.DataAudit;

import javax.ejb.Stateless;

@Stateless
public class DataAuditDaoImpl extends BaseAuditDao<DataAudit, Long> implements DataAuditDao {
    public DataAuditDaoImpl() {
        super(DataAudit.class);
    }
}
