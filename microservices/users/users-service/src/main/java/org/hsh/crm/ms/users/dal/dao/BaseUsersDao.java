package org.hsh.crm.ms.users.dal.dao;

import org.hsh.common.dal.entity.IdEntity;
import org.hsh.crm.ms.common.bll.services.audit.DataAuditService;
import org.hsh.crm.ms.common.dal.dao.BaseAuditableCrudDao;
import org.hsh.crm.ms.users.dal.DalConstants;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public class BaseUsersDao<E extends IdEntity, K extends Serializable> extends BaseAuditableCrudDao<E, K> {
    @PersistenceContext(unitName = DalConstants.PERSISTENCE_UNIT)
    private EntityManager em;

    public BaseUsersDao(Class<E> entityClass) {
        super(entityClass);
    }

    @EJB
    private DataAuditService sDataAudit;

    @Override
    protected DataAuditService getDataAuditService() {
        return sDataAudit;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
