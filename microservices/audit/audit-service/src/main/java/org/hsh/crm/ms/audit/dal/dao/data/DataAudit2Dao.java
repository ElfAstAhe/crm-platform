package org.hsh.crm.ms.audit.dal.dao.data;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.dao.base.BaseAuditDao;
import org.hsh.crm.ms.audit.dal.entities.DataAudit2;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class DataAudit2Dao extends BaseAuditDao<DataAudit2, Long> implements CrudDao<DataAudit2, Long> {
    public DataAudit2Dao() {
        super(DataAudit2.class);
    }
}
