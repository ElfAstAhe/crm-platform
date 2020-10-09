package dal.dao;

import common.dal.dao.CrudDao;
import dal.entities.DataAudit;

import javax.ejb.Local;

@Local
public interface DataAuditDao extends CrudDao<DataAudit, Long> {
}
