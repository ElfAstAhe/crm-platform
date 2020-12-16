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
    public void remove(Object id) {
        if (id == null)
            throw new NullPointerException();
        Entity entity = find(id);
        if (entity == null)
            return;
        entity.setDeleteFlag(1);
        
        getEntityManager().merge(entity);
    }
}
