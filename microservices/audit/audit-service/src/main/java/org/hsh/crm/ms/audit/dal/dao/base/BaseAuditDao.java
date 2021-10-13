package org.hsh.crm.ms.audit.dal.dao.base;

import org.hsh.common.dal.entity.IdEntity;
import org.hsh.crm.ms.audit.dal.DalConstants;
import org.hsh.ms.common.dal.dao.BaseCrudDao;
import org.hsh.ms.common.dal.dao.DaoUtils;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.time.OffsetDateTime;

public abstract class BaseAuditDao<E extends IdEntity,K extends Serializable> extends BaseCrudDao<E, K> {
    @PersistenceContext(unitName = DalConstants.PERSISTENCE_UNIT)
    private EntityManager em;

    protected BaseAuditDao(Class<E> entityClass) {
        super(entityClass);
    }

    public Long getEarlyCount(OffsetDateTime earlyMark) {
        return getEntityManager().createQuery("select count(e) from " + DaoUtils.getEntityName(getEntityClass()) + " e where e.event_date < :event_date", Long.class)
                .setLockMode(LockModeType.NONE)
                .setParameter("event_date", earlyMark)
                .getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
