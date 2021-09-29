package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.AsyncCrudDao;
import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.SecurityAuditHistory;

import javax.ejb.Local;

@Local
public interface SecurityAuditHistoryDao extends CrudDao<SecurityAuditHistory, Long>, AsyncCrudDao<SecurityAuditHistory, Long> {
}
