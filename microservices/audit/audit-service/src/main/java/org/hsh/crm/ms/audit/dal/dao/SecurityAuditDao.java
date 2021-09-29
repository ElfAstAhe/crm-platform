package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.AsyncCrudDao;
import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.SecurityAudit;

import javax.ejb.Local;

@Local
public interface SecurityAuditDao extends CrudDao<SecurityAudit, Long>, AsyncCrudDao<SecurityAudit, Long> {
}
