package org.hsh.crm.ms.audit.bll.repository;

import org.hsh.common.bll.repository.AsyncCrudRepository;
import org.hsh.common.bll.repository.CrudRepository;
import org.hsh.crm.ms.audit.bll.model.DataAudit;

import javax.ejb.Local;

@Local
public interface DataAuditRepository extends CrudRepository<DataAudit, Long>, AsyncCrudRepository<DataAudit, Long> {
}
