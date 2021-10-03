package org.hsh.crm.ms.audit.dal.repository;

import org.hsh.crm.ms.audit.bll.model.Audit;
import org.hsh.crm.ms.audit.bll.model.DataAudit;
import org.hsh.crm.ms.audit.bll.model.SecurityAudit;
import org.hsh.crm.ms.audit.bll.repository.AuditRepository;
import org.hsh.crm.ms.audit.bll.repository.DataAuditRepository;
import org.hsh.crm.ms.audit.bll.repository.SecurityAuditRepository;
import org.hsh.crm.ms.audit.dal.entities.convertor.DataAuditConvertor;
import org.hsh.crm.ms.audit.dal.entities.convertor.SecurityAuditConvertor;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class AuditRepositoryImpl implements AuditRepository {
    @EJB
    private DataAuditRepository repoDataAudit;

    @EJB
    private SecurityAuditRepository repoSecurityAudit;

    public List<Audit> listAll() throws ExecutionException, InterruptedException {
        Future<List<DataAudit>> dataAuditList = repoDataAudit.listAllAsync();
        Future<List<SecurityAudit>> securityAuditList = repoSecurityAudit.listAllAsync();
        return Stream.concat(dataAuditList.get()
                                          .stream()
                                          .map(DataAuditConvertor::toAudit),
                             securityAuditList.get()
                                              .stream()
                                              .map(SecurityAuditConvertor::toAudit))
                     .filter(Objects::nonNull)
                     .sorted(Comparator.comparing(Audit::getEventDate))
                     .collect(Collectors.toList());
    }
}
