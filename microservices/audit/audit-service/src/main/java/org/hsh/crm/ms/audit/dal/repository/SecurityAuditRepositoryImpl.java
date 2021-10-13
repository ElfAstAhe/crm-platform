package org.hsh.crm.ms.audit.dal.repository;

import org.hsh.common.exceptions.runtime.base.DalRuntimeException;
import org.hsh.crm.ms.audit.bll.model.SecurityAudit;
import org.hsh.crm.ms.audit.bll.repository.AuditSettingsRepository;
import org.hsh.crm.ms.audit.bll.repository.SecurityAuditRepository;
import org.hsh.crm.ms.audit.bll.settings.AuditSettingsEnum;
import org.hsh.crm.ms.audit.dal.dao.AuditDaoStrategyKeyEnum;
import org.hsh.crm.ms.audit.dal.dao.security.SecurityAuditDaoStrategy;
import org.hsh.crm.ms.audit.dal.entities.convertor.SecurityAuditConvertor;

import javax.annotation.PostConstruct;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Stateless
public class SecurityAuditRepositoryImpl implements SecurityAuditRepository {
    @EJB
    private AuditSettingsRepository repoSettings;

    @Inject
    private Instance<SecurityAuditDaoStrategy> strategies;

    private SecurityAuditDaoStrategy strategy;

    @PostConstruct
    public void postConstruct() {
        strategy = selectCurrentStrategy();
    }

    @Override
    @Asynchronous
    public Future<SecurityAudit> getAsync(Object id) {
        return new AsyncResult<>(get(id));
    }

    @Override
    @Asynchronous
    public Future<SecurityAudit> getByKeyAsync(Long key) {
        return new AsyncResult<>(getByKey(key));
    }

    @Override
    @Asynchronous
    public Future<SecurityAudit> saveAsync(SecurityAudit model) {
        return new AsyncResult<>(save(model));
    }

    @Override
    @Asynchronous
    public void removeAsync(Object id) {
        remove(id);
    }

    @Override
    @Asynchronous
    public Future<List<SecurityAudit>> listAllAsync() {
        return new AsyncResult<>(listAll());
    }

    @Override
    public SecurityAudit get(Object id) {
        return SecurityAuditConvertor.toModel(strategy.find(id));
    }

    @Override
    public SecurityAudit getByKey(Long key) {
        return SecurityAuditConvertor.toModel(strategy.findByKey(key));
    }

    @Override
    public SecurityAudit save(SecurityAudit model) {
        if(model == null)
            return null;
        if(model.getId() == null)
            return SecurityAuditConvertor.toModel(strategy.create(SecurityAuditConvertor.toEntity(model, strategy.getStrategyEntityClass())));

        return SecurityAuditConvertor.toModel(strategy.edit(SecurityAuditConvertor.toEntity(model, strategy.getStrategyEntityClass())));
    }

    @Override
    public void remove(Object id) {
        strategy.remove(id);
    }

    @Override
    public List<SecurityAudit> listAll() {
        return strategy.listAll()
                       .stream()
                       .map(SecurityAuditConvertor::toModel)
                       .collect(Collectors.toList());
    }

    private SecurityAuditDaoStrategy selectCurrentStrategy() {
        AuditDaoStrategyKeyEnum key = AuditDaoStrategyKeyEnum.valueOf(repoSettings.getStringValue(AuditSettingsEnum.SECURITY_AUDIT_STRATEGY));
        return strategies.stream()
                         .filter(s -> key.equals(s.getStrategyKey()))
                         .findFirst()
                         .orElseThrow(() -> new DalRuntimeException("unknown security audit strategy"));
    }
}
