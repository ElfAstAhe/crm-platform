package org.hsh.crm.ms.common.dao;

import org.hsh.crm.ms.audit.dto.builders.DataAuditBuilder;
import org.hsh.common.dal.entity.IdEntity;
import org.hsh.ms.common.dal.dao.BaseCrudDao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;

/**
 * базовый crud dao с функциональностью аудита
 *
 * @param <Entity> entity class
 * @param <Key> key class
 */
public abstract class BaseAuditableCrudDao<Entity extends IdEntity, Key extends Serializable> extends BaseCrudDao<Entity, Key> {
    private DataAuditClient dataAuditClient;

    public BaseAuditableCrudDao(Class<Entity> entityClass) {
        super(entityClass);
    }

    public BaseAuditableCrudDao(Class<Entity> entityClass, DaoHelper<Entity> daoHelper, String auditClientBaseUri) {
        super(entityClass, daoHelper);
        dataAuditClient = new DataAuditClient(getAuditUri(), getExecutorService());
    }

    @PostConstruct
    public void postConstruct() {
        dataAuditClient = new DataAuditClient(getAuditUri(), getExecutorService());
    }

    @PreDestroy
    public void preDestroy() {
        dataAuditClient.close();
    }

    @Override
    public Entity create(Entity entity) {
        final Entity result = super.create(entity);

        dataAuditClient.createInstanceAsync(DataAuditBuilder.get()
                .setCreatedInstance(result)
                .build());

        return result;
    }

    @Override
    public Entity edit(Entity entity) {
        final Entity before = find(entity.getId());
        Entity result = super.edit(entity);

        dataAuditClient.createInstanceAsync(DataAuditBuilder.get()
                .setModifiedInstance(before, result)
                .build());

        return result;
    }

    @Override
    public void remove(Object id) {
        final Entity before = find(id);
        super.remove(id);

        dataAuditClient.createInstanceAsync(DataAuditBuilder.get()
                .setRemovedInstance(before)
                .build());
    }

    protected abstract String getAuditUri();
    protected abstract ExecutorService getExecutorService();
}
