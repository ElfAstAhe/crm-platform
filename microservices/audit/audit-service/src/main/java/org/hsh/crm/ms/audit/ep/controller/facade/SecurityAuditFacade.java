package org.hsh.crm.ms.audit.ep.controller.facade;

import org.hsh.crm.ms.audit.dal.dao.SecurityAuditDao;
import org.hsh.crm.ms.audit.dto.SecurityAudit;
import org.hsh.crm.ms.audit.ep.dto.converter.SecurityAuditConverter;
import org.hsh.ms.common.ep.facade.CrudFacade;
import org.hsh.ms.common.exceptions.runtime.ep.ResourceGoneException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class SecurityAuditFacade implements CrudFacade<SecurityAudit> {
    @EJB
    private SecurityAuditDao daoSecurityAudit;

    @Override
    public SecurityAudit get(Object id) {
        return SecurityAuditConverter.toDto(daoSecurityAudit.find(id));
    }

    @Override
    public List<SecurityAudit> listAll() {
        throw new ResourceGoneException("not implemented");
    }

    @Override
    public SecurityAudit create(SecurityAudit instance) {
        return SecurityAuditConverter.toDto(daoSecurityAudit.create(SecurityAuditConverter.toEntity(instance, null)));
    }

    @Override
    public SecurityAudit edit(Object id, SecurityAudit instance) {
        return SecurityAuditConverter.toDto(daoSecurityAudit.edit(SecurityAuditConverter.toEntity(instance, daoSecurityAudit::find)));
    }

    @Override
    public void remove(Object id) {
        daoSecurityAudit.remove(id);
    }
}
