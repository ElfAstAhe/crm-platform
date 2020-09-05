package common.dal.dao;

import common.dal.entity.MarkDelete;

/**
 * base mark delete dictionary crud dao
 *
 * @author elf
 * @param <TEntity> entity
 * @param <TKey> key
 */
public abstract class BaseMarkDeleteDao<TEntity extends MarkDelete, TKey>
        extends BaseCrudDao<TEntity, TKey>{
    
    public BaseMarkDeleteDao(Class<TEntity> entityClass) {
        super(entityClass);
    }
    
    @Override
    public void remove(TEntity entity) {
        if (entity == null)
            throw new NullPointerException();
        
        entity.setDeleteFlag(1);
        
        getEntityManager().merge(entity);
    }
}
