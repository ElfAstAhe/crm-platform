package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.AsyncCrudDao;
import org.hsh.crm.ms.audit.dal.entities.DataAudit;
import org.hsh.common.dal.dao.CrudDao;

import javax.ejb.Local;

@Local
public interface DataAuditDao extends CrudDao<DataAudit, Long>, AsyncCrudDao<DataAudit, Long> {
}
