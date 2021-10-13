package org.hsh.crm.ms.audit.ep.controller.facade;

import org.hsh.crm.ms.audit.bll.repository.SecurityAuditRepository;
import org.hsh.crm.ms.audit.dto.SecurityAudit;
import org.hsh.crm.ms.audit.dto.convertor.SecurityAuditConvertor;
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
    private SecurityAuditRepository repoSecurityAudit;

    @Override
    public SecurityAudit get(Object id) {
        return SecurityAuditConvertor.toDto(repoSecurityAudit.get(id));
    }

    @Override
    public List<SecurityAudit> listAll() {
        throw new ResourceGoneException("not implemented");
    }

    @Override
    public SecurityAudit create(SecurityAudit instance) {
        return SecurityAuditConvertor.toDto(repoSecurityAudit.save(SecurityAuditConvertor.toModel(instance)));
    }

    @Override
    public SecurityAudit edit(Object id, SecurityAudit instance) {
        return SecurityAuditConvertor.toDto(repoSecurityAudit.save(SecurityAuditConvertor.toModel(instance)));
    }

    @Override
    public void remove(Object id) {
        repoSecurityAudit.remove(id);
    }
}
