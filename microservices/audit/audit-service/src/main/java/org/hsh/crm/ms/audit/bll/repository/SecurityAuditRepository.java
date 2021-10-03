package org.hsh.crm.ms.audit.bll.repository;

import org.hsh.common.bll.repository.AsyncCrudRepository;
import org.hsh.common.bll.repository.CrudRepository;
import org.hsh.crm.ms.audit.bll.model.SecurityAudit;

import javax.ejb.Local;

@Local
public interface SecurityAuditRepository extends CrudRepository<SecurityAudit, Long>, AsyncCrudRepository<SecurityAudit, Long> {
}
