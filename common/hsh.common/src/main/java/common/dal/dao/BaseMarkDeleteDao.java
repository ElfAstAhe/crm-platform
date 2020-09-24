package common.dal.dao;

import common.dal.entity.MarkDelete;

import java.io.Serializable;

/**
 * base mark delete dictionary crud dao
 *
 * @author elf
 * @param <Entity> entity
 * @param <Key>    unique key
 */
public abstract class BaseMarkDeleteDao<Entity extends MarkDelete, Key extends Serializable>
        extends BaseCrudDao<Entity, Key>{
    
    public BaseMarkDeleteDao(Class<Entity> entityClass) {
        super(entityClass);
    }
    
    @Override
    public void remove(Entity entity) {
        if (entity == null)
            throw new NullPointerException();
        
        entity.setDeleteFlag(1);
        
        getEntityManager().merge(entity);
    }
}
