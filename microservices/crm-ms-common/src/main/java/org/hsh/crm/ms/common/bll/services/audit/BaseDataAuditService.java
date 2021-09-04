package org.hsh.crm.ms.common.bll.services.audit;

import org.hsh.crm.ms.audit.client.DataAuditClient;
import org.hsh.crm.ms.audit.dto.DataAudit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.enterprise.concurrent.ManagedExecutorService;

public abstract class BaseDataAuditService implements DataAuditService{
    @Resource
    private ManagedExecutorService sExecutor;

    private DataAuditClient client;

    @PostConstruct
    public void postConstruct() {
        client = new DataAuditClient(getClientParams().getBaseUri(),
                                     getClientParams().getConnectionTimeoutMillis(),
                                     getClientParams().getReadTimeoutMillis(),
                                     getClientParams().getJwtSupplier(),
                                     getClientParams().getSslHostnameVerifier(),
                                     sExecutor);
    }

    @PreDestroy
    public void preDestroy() {
        client.close();
    }

    @Override
    public void audit(DataAudit dto) {
        client.create(dto);
    }

    @Override
    @Asynchronous
    public void auditAsync(DataAudit dto) {
        client.createAsync(dto);
    }

    protected abstract AuditClientParams getClientParams();
}
