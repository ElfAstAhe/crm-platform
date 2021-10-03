package org.hsh.crm.ms.audit.dal.repository;

import org.hsh.common.exceptions.runtime.base.DalRuntimeException;
import org.hsh.crm.ms.audit.bll.model.DataAudit;
import org.hsh.crm.ms.audit.bll.repository.AuditSettingsRepository;
import org.hsh.crm.ms.audit.bll.repository.DataAuditRepository;
import org.hsh.crm.ms.audit.bll.settings.AuditSettingsEnum;
import org.hsh.crm.ms.audit.dal.dao.AuditDaoStrategyKeyEnum;
import org.hsh.crm.ms.audit.dal.dao.DataAuditDaoStrategy;
import org.hsh.crm.ms.audit.dal.entities.convertor.DataAuditConvertor;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Stateless
public class DataAuditRepositoryImpl implements DataAuditRepository {
    @EJB
    private AuditSettingsRepository repoSettings;

    @Inject
    private Instance<DataAuditDaoStrategy> strategies;

    private DataAuditDaoStrategy strategy;

    @PostConstruct
    public void postConstruct() {
        strategy = selectCurrentStrategy();
    }

    @Override
    @Asynchronous
    public Future<DataAudit> getAsync(Object id) {
        return new AsyncResult<>(get(id));
    }

    @Override
    @Asynchronous
    public Future<DataAudit> getByKeyAsync(Long key) {
        return new AsyncResult<>(getByKey(key));
    }

    @Override
    @Asynchronous
    public Future<DataAudit> saveAsync(DataAudit model) {
        return new AsyncResult<>(save(model));
    }

    @Override
    @Asynchronous
    public void removeAsync(Object id) {
        remove(id);
    }

    @Override
    @Asynchronous
    public Future<List<DataAudit>> listAllAsync() {
        return new AsyncResult<>(listAll());
    }

    @Override
    public DataAudit get(Object id) {
        return DataAuditConvertor.toModel(strategy.find(id));
    }

    @Override
    public DataAudit getByKey(Long key) {
        return DataAuditConvertor.toModel(strategy.findByKey(key));
    }

    @Override
    public DataAudit save(DataAudit model) {
        if(model == null)
            return null;
        if(model.getId() == null)
            return DataAuditConvertor.toModel(strategy.create(DataAuditConvertor.toEntity(model, strategy.getStrategyEntityClass())));

        return DataAuditConvertor.toModel(strategy.edit(DataAuditConvertor.toEntity(model, strategy.getStrategyEntityClass())));
    }

    @Override
    public void remove(Object id) {
        strategy.remove(id);
    }

    @Override
    public List<DataAudit> listAll() {
        return strategy.listAll()
                       .stream()
                       .map(DataAuditConvertor::toModel)
                       .collect(Collectors.toList());
    }

    private DataAuditDaoStrategy selectCurrentStrategy() {
        AuditDaoStrategyKeyEnum key = AuditDaoStrategyKeyEnum.valueOf(repoSettings.getStringValue(AuditSettingsEnum.DATA_AUDIT_STRATEGY));
        for(DataAuditDaoStrategy strategy : strategies) {
            if(key.equals(strategy.getStrategyKey()))
                return strategy;
        }

        throw new DalRuntimeException("unknown data audit strategy");
    }
}
