package microservice.common.dal.dao;

import common.dal.dao.BaseCrudDao;
import common.dal.dao.DaoHelper;
import common.dal.entity.IdEntity;

import java.io.Serializable;

/**
 *
 *
 * @param <Entity> entity class
 * @param <Key> key class
 */
public abstract class BaseAuditableCrudDao<Entity extends IdEntity, Key extends Serializable> extends BaseCrudDao<Entity, Key> {
    public BaseAuditableCrudDao(Class<Entity> entityClass) {
        super(entityClass);
    }

    public BaseAuditableCrudDao(Class<Entity> entityClass, DaoHelper<Entity> daoHelper) {
        super(entityClass, daoHelper);
    }
}
