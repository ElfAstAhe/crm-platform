package org.hsh.crm.ms.study.ps.dal.dao;

import org.hsh.crm.ms.study.ps.dal.DalConstants;
import org.hsh.crm.ms.study.ps.dal.entities.TestData;
import org.hsh.ms.common.dal.dao.BaseCrudDao;

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
