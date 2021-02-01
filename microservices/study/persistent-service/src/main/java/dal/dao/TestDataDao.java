package dal.dao;

import common.dal.dao.CrudDao;
import dal.entities.TestData;

import javax.ejb.Local;

@Local
public interface TestDataDao extends CrudDao<TestData, String> {
}
