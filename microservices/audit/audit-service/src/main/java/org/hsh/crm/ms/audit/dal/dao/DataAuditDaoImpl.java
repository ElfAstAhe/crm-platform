package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.crm.ms.audit.dal.DalConstants;
import org.hsh.crm.ms.audit.dal.entities.DataAudit;
import org.hsh.ms.common.dal.dao.BaseCrudDao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DataAuditDaoImpl extends BaseAuditDaoImpl<DataAudit, Long> implements DataAuditDao {
    @PersistenceContext(unitName = DalConstants.PERSISTENCE_UNIT)
    private EntityManager em;

    public DataAuditDaoImpl() {
        super(DataAudit.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
