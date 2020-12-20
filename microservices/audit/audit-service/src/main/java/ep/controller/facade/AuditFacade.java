package ep.controller.facade;

import bll.repository.AuditRepository;
import dto.audit.Audit;
import ep.dto.converter.AuditConverter;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class AuditFacade {
    @EJB
    private AuditRepository repoAudit;

    public List<Audit> listAll() throws ExecutionException, InterruptedException {
        return repoAudit.listAll()
                .stream()
                .map(AuditConverter::toDto)
                .collect(Collectors.toList());
    }
}
