package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.entity.IdEntity;
import org.hsh.ms.common.dal.dao.BaseCrudDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public abstract class BaseAuditDao<E extends IdEntity,I extends Serializable> extends BaseCrudDao<E, I> {
    @PersistenceContext(unitName = "audit.PU")
    private EntityManager em;

    protected BaseAuditDao(Class<E> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
