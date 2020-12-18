package dal.repository;

import bll.model.Audit;
import bll.repository.AuditRepository;
import dal.dao.DataAuditDao;
import dal.dao.SecurityAuditDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AuditRepositoryImpl implements AuditRepository {
    @EJB
    private DataAuditDao daoDataAudit;

    @EJB
    private SecurityAuditDao daoSecurityAudit;

    public List<Audit> getDataTest() {
        return null;
    }
}
