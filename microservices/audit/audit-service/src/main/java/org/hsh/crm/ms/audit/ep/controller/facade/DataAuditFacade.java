package org.hsh.crm.ms.audit.ep.controller.facade;

import org.hsh.crm.ms.audit.bll.repository.DataAuditRepository;
import org.hsh.crm.ms.audit.dto.DataAudit;
import org.hsh.crm.ms.audit.dto.convertor.DataAuditConvertor;
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
    private DataAuditRepository repoDataAudit;

    @Override
    public DataAudit get(Object id) {
        return DataAuditConvertor.toDto(repoDataAudit.get(id));
    }

    @Override
    public List<DataAudit> listAll() {
        throw new ResourceGoneException("not implemented");
    }

    @Override
    public DataAudit create(DataAudit instance) {
        return DataAuditConvertor.toDto(repoDataAudit.save(DataAuditConvertor.toModel(instance)));
    }

    @Override
    public DataAudit edit(Object id, DataAudit instance) {
        return DataAuditConvertor.toDto(repoDataAudit.save(DataAuditConvertor.toModel(instance)));
    }

    @Override
    public void remove(Object id) {
        repoDataAudit.remove(id);
    }
}
