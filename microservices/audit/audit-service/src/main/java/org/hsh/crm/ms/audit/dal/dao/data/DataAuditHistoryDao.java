package org.hsh.crm.ms.audit.dal.dao.data;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.DataAuditHistory;

import javax.ejb.Local;

@Local
public interface DataAuditHistoryDao extends CrudDao<DataAuditHistory, Long> {
}
