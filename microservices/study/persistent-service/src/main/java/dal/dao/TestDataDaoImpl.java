package dal.dao;

import common.dal.dao.BaseCrudDao;
import dal.DalConstants;
import dal.entities.TestData;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

@Stateless
public class TestDataDaoImpl extends BaseCrudDao<TestData, String> implements TestDataDao{
    @PersistenceContext(unitName = DalConstants.PERSISTENCE_UNIT)
    private EntityManager em;

    public TestDataDaoImpl() {
        super(TestData.class);
    }

    @Override
    public TestData findByKey(String key) {
        return em.createQuery("select td from TestData td where td.dummy = :dummy", TestData.class)
                .setLockMode(LockModeType.NONE)
                .setParameter("dummy", key)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
