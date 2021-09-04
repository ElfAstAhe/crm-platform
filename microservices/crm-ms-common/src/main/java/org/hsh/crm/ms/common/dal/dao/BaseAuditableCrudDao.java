package org.hsh.crm.ms.common.dal.dao;

import org.hsh.crm.ms.audit.dto.builders.DataAuditBuilder;
import org.hsh.common.dal.entity.IdEntity;
import org.hsh.crm.ms.common.bll.services.audit.DataAuditService;
import org.hsh.ms.common.dal.dao.BaseCrudDao;

import javax.ejb.EJB;
import java.io.Serializable;

/**
 * базовый crud dao с функциональностью аудита
 *
 * @param <E> entity class
 * @param <K> key class
 */
public abstract class BaseAuditableCrudDao<E extends IdEntity, K extends Serializable> extends BaseCrudDao<E, K> {
    public BaseAuditableCrudDao(Class<E> entityClass) {
        super(entityClass);
    }

    @Override
    public E create(E entity) {
        final E result = super.create(entity);

        getDataAuditService().auditAsync(DataAuditBuilder.get()
                                                         .setCreatedInstance(result)
                                                         .build());
        return result;
    }

    @Override
    public E edit(E entity) {
        final E before = find(entity.getId());
        E result = super.edit(entity);

        getDataAuditService().auditAsync(DataAuditBuilder.get()
                                                         .setModifiedInstance(before, result)
                                                         .build());

        return result;
    }

    @Override
    public void remove(Object id) {
        final E before = find(id);
        super.remove(id);

        getDataAuditService().auditAsync(DataAuditBuilder.get()
                                                         .setRemovedInstance(before)
                                                         .build());
    }

    protected abstract DataAuditService getDataAuditService();
}
