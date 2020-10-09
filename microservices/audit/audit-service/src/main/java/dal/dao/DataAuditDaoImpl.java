package dal.dao;

import common.dal.dao.BaseCrudDao;
import dal.entities.DataAudit;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DataAuditDaoImpl extends BaseCrudDao<DataAudit, Long> implements DataAuditDao {
    @PersistenceContext(unitName = "")
    private EntityManager em;

    public DataAuditDaoImpl() {
        super(DataAudit.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
