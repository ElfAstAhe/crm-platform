package microservice.common.dal.dao;

import client.audit.DataAuditClient;
import common.dal.dao.BaseCrudDao;
import common.dal.dao.DaoHelper;
import common.dal.entity.IdEntity;
import dto.audit.builders.DataAuditBuilder;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import java.io.Serializable;

/**
 * базовый crud dao с функциональностью аудита
 *
 * @param <Entity> entity class
 * @param <Key> key class
 */
public abstract class BaseAuditableCrudDao<Entity extends IdEntity, Key extends Serializable> extends BaseCrudDao<Entity, Key> {
    @Resource
    private ManagedExecutorService executorService;

    private final DataAuditClient dataAuditClient;

    public BaseAuditableCrudDao(Class<Entity> entityClass, String auditClientBaseUri) {
        super(entityClass);
        dataAuditClient = new DataAuditClient(auditClientBaseUri, executorService);
    }

    public BaseAuditableCrudDao(Class<Entity> entityClass, DaoHelper<Entity> daoHelper, String auditClientBaseUri) {
        super(entityClass, daoHelper);
        dataAuditClient = new DataAuditClient(auditClientBaseUri, executorService);
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
}
