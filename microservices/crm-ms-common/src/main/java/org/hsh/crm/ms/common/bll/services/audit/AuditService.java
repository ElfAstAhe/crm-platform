package org.hsh.crm.ms.common.bll.services.audit;

/**
 * базовый интерфейс аудита
 * @param <D> dto аудита
 */
public interface AuditService<D> {
    void audit(D dto);
    void auditAsync(D dto);
}
