package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.entity.IdEntity;
import org.hsh.crm.ms.audit.dal.DalConstants;
import org.hsh.ms.common.dal.dao.BaseEJBAsyncCrudDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public abstract class BaseAsyncAuditDao<E extends IdEntity, K extends Serializable> extends BaseEJBAsyncCrudDao<E, K> {
    @PersistenceContext(unitName = DalConstants.PERSISTENCE_UNIT)
    private EntityManager em;

    protected BaseAsyncAuditDao(Class<E> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
