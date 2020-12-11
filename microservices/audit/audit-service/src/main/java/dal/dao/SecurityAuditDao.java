package dal.dao;

import common.dal.dao.CrudDao;
import dal.entities.SecurityAudit;

import javax.ejb.Local;

@Local
public interface SecurityAuditDao extends CrudDao<SecurityAudit, Long> {
}
