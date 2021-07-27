package dal.dao;

import dal.entities.DataAudit;
import org.hsh.common.dal.dao.CrudDao;

import javax.ejb.Local;

@Local
public interface DataAuditDao extends CrudDao<DataAudit, Long> {
}
