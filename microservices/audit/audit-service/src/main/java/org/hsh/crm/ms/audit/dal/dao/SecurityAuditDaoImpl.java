package org.hsh.crm.ms.audit.dal.dao;

import common.dal.dao.BaseCrudDao;
import org.hsh.crm.ms.audit.dal.DalConstants;
import org.hsh.crm.ms.audit.dal.entities.SecurityAudit;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SecurityAuditDaoImpl extends BaseCrudDao<SecurityAudit, Long> implements SecurityAuditDao {
    @PersistenceContext(unitName = DalConstants.PERSISTENCE_UNIT)
    private EntityManager em;

    public SecurityAuditDaoImpl() {
        super(SecurityAudit.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
