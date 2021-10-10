package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.DataAudit1;
import org.hsh.ms.common.dal.dao.DaoUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class DataAudit1Dao extends BaseAuditDao<DataAudit1, Long> implements CrudDao<DataAudit1, Long> {
    public DataAudit1Dao() {
        super(DataAudit1.class);
    }
}
