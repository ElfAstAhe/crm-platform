package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.entity.IdEntity;
import org.hsh.ms.common.dal.dao.BaseCrudDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public class BaseAuditDaoImpl<E extends IdEntity,I extends Serializable> extends BaseCrudDao<E, I> {
    @PersistenceContext(unitName = )


    @Override
    protected EntityManager getEntityManager() {
        return null;
    }
}
