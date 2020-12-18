package ep.controller.facade;

import bll.repository.AuditRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class AuditFacade {
    @EJB
    private AuditRepository repoAudit;
}
