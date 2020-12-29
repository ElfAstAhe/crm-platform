package ep.controller.facade;

import common.ep.facade.CrudFacade;
import common.exceptions.runtime.ep.ResourceGoneException;
import dal.dao.SecurityAuditDao;
import dto.audit.SecurityAudit;
import ep.dto.converter.SecurityAuditConverter;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class SecurityAuditFacade implements CrudFacade<SecurityAudit> {
    @EJB
    private SecurityAuditDao daoSecurityAudit;

    @Override
    public SecurityAudit getInstance(Object id) {
        return SecurityAuditConverter.toDto(daoSecurityAudit.find(id));
    }

    @Override
    public List<SecurityAudit> listAllInstances() {
//        return daoSecurityAudit.listAll()
//                .stream()
//                .map(SecurityAuditConverter::toDto)
//                .collect(Collectors.toList());
        throw new ResourceGoneException("not implemented");
    }

    @Override
    public SecurityAudit createInstance(SecurityAudit instance) {
        return SecurityAuditConverter.toDto(daoSecurityAudit.create(SecurityAuditConverter.toEntity(instance, null)));
    }

    @Override
    public SecurityAudit editInstance(Object id, SecurityAudit instance) {
        return SecurityAuditConverter.toDto(daoSecurityAudit.edit(SecurityAuditConverter.toEntity(instance, daoSecurityAudit::find)));
    }

    @Override
    public void removeInstance(Object id) {
        daoSecurityAudit.remove(id);
    }
}
