package org.hsh.crm.ms.study.ps.ep.controller.facade;

import org.hsh.crm.ms.study.ps.dal.dao.TestDataDao;
import org.hsh.crm.ms.study.ps.dto.TestDto;
import org.hsh.crm.ms.study.ps.dto.convertor.TestDtoConvertor;
import org.hsh.ms.common.ep.facade.CrudFacade;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class TestFacade implements CrudFacade<TestDto> {
    @EJB
    private TestDataDao daoTestData;

    @Override
    public TestDto get(Object id) {
        return TestDtoConvertor.toDto(daoTestData.find(id));
    }

    @Override
    public List<TestDto> listAll() {
        return null;
    }

    @Override
    public TestDto create(TestDto dto) {
        return null;
    }

    @Override
    public TestDto edit(Object id, TestDto dto) {
        return null;
    }

    @Override
    public void remove(Object id) {

    }
}
