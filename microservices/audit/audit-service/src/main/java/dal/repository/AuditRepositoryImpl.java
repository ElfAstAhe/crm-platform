package dal.repository;

import bll.model.Audit;
import bll.repository.AuditRepository;
import dal.dao.DataAuditDao;
import dal.dao.SecurityAuditDao;
import dal.entities.DataAudit;
import dal.entities.SecurityAudit;
import dal.entities.converter.DataAuditConverter;
import dal.entities.converter.SecurityAuditConverter;

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
    private DataAuditDao daoDataAudit;

    @EJB
    private SecurityAuditDao daoSecurityAudit;

    public List<Audit> listAll() throws ExecutionException, InterruptedException {
        Future<List<DataAudit>> dataAuditList = daoDataAudit.listAllAsync();
        Future<List<SecurityAudit>> securityAuditList = daoSecurityAudit.listAllAsync();
        return Stream.concat(dataAuditList.get()
                        .stream()
                        .map(DataAuditConverter::toAudit),
                    securityAuditList.get()
                        .stream()
                        .map(SecurityAuditConverter::toAudit))
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Audit::getEventDate))
                .collect(Collectors.toList());
    }
}
