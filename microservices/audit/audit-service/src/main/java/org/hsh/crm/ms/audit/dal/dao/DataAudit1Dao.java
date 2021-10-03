package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.DataAudit1;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class DataAudit1Dao extends BaseAuditDao<DataAudit1, Long> implements CrudDao<DataAudit1, Long> {
    public DataAudit1Dao() {
        super(DataAudit1.class);
    }
}
