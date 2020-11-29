package common.dal.dao;

import test.MockSimpleEntity;

import javax.persistence.EntityManager;

public class MockBaseCrudDao extends BaseCrudDao<MockSimpleEntity,Long> {
    private final EntityManager em;

    public MockBaseCrudDao(EntityManager em) {
        super(MockSimpleEntity.class);
        this.em = em;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
