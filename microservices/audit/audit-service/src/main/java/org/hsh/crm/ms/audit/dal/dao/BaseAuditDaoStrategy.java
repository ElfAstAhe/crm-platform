package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.common.dal.dao.FilterParams;
import org.hsh.common.dal.entity.IdEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseAuditDaoStrategy<B extends IdEntity, E extends B, K extends Serializable> implements CrudDao<B, K> {
    private final Class<E> entityClass;

    public BaseAuditDaoStrategy(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public B find(Object id) {
        return getDao().find(id);
    }

    @Override
    public B findByKey(K key) {
        return getDao().findByKey(key);
    }

    @Override
    public List<B> listAll() {
        return new ArrayList<>(getDao().listAll());
    }

    @Override
    public List<B> listFiltered(FilterParams params) {
        return new ArrayList<>(getDao().listFiltered(params));
    }

    @Override
    public boolean exists(Object id) {
        return getDao().exists(id);
    }

    @Override
    public B create(B entity) {
        return getDao().create(entityClass.cast(entity));
    }

    @Override
    public B edit(B entity) {
        return getDao().edit(entityClass.cast(entity));
    }

    @Override
    public void remove(Object id) {
        getDao().remove(id);
    }

    @Override
    public void remove(B entity) {
        getDao().remove(entityClass.cast(entity));
    }

    @Override
    public Long count() {
        return getDao().count();
    }

    @Override
    public Long count(FilterParams params) {
        return getDao().count(params);
    }

    protected Class<E> getEntityClass() {
        return entityClass;
    }

    protected abstract CrudDao<E, K> getDao();
}
