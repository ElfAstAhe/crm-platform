package org.hsh.crm.ms.audit.ep.controller.facade;

import org.hsh.crm.ms.audit.bll.repository.AuditRepository;
import org.hsh.crm.ms.audit.dto.Audit;
import org.hsh.crm.ms.audit.ep.dto.convertor.AuditConvertor;

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
                        .map(AuditConvertor::toDto)
                        .collect(Collectors.toList());
    }
}
