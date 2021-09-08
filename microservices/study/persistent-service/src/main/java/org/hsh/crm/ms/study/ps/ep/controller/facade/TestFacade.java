package org.hsh.crm.ms.study.ps.ep.controller.facade;

import org.hsh.crm.ms.study.ps.ep.dto.TestDto;
import org.hsh.ms.common.ep.facade.CrudFacade;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class TestFacade implements CrudFacade<TestDto> {
    @Override
    public TestDto get(Object id) {
        return null;
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
