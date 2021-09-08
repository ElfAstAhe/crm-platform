package org.hsh.crm.ms.study.ps.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.study.ps.dal.entities.TestData;

import javax.ejb.Local;

@Local
public interface TestDataDao extends CrudDao<TestData, String> {
}
