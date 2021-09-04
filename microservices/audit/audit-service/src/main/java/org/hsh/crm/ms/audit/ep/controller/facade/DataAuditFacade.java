package org.hsh.crm.ms.audit.ep.controller.facade;

import org.hsh.crm.ms.audit.dal.dao.DataAuditDao;
import org.hsh.crm.ms.audit.dto.DataAudit;
import org.hsh.crm.ms.audit.ep.dto.converter.DataAuditConverter;
import org.hsh.ms.common.ep.facade.CrudFacade;
import org.hsh.ms.common.exceptions.runtime.ep.ResourceGoneException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class DataAuditFacade implements CrudFacade<DataAudit> {
    @EJB
    private DataAuditDao daoDataAudit;

    @Override
    public DataAudit get(Object id) {
        return DataAuditConverter.toDto(daoDataAudit.find(id));
    }

    @Override
    public List<DataAudit> listAll() {
        throw new ResourceGoneException("not implemented");
    }

    @Override
    public DataAudit create(DataAudit instance) {
        return DataAuditConverter.toDto(daoDataAudit.create(DataAuditConverter.toEntity(instance, null)));
    }

    @Override
    public DataAudit edit(Object id, DataAudit instance) {
        return DataAuditConverter.toDto(daoDataAudit.edit(DataAuditConverter.toEntity(instance, daoDataAudit::find)));
    }

    @Override
    public void remove(Object id) {
        daoDataAudit.remove(id);
    }
}
