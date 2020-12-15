package ep.controller.facade;

import common.ep.facade.CrudFacade;
import dto.audit.SecurityAudit;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class SecurityAuditFacade implements CrudFacade<SecurityAudit> {
    @Override
    public SecurityAudit getInstance(Object id) {
        return null;
    }

    @Override
    public List<SecurityAudit> listAllInstances() {
        return null;
    }

    @Override
    public SecurityAudit createInstance(SecurityAudit instance) {
        return null;
    }

    @Override
    public SecurityAudit editInstance(Object id, SecurityAudit instance) {
        return null;
    }

    @Override
    public void removeInstance(Object id) {

    }
}
