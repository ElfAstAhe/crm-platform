package org.hsh.crm.ms.study.ps.ep.controller;

import org.hsh.crm.ms.study.ps.ep.controller.facade.TestFacade;
import org.hsh.crm.ms.study.ps.dto.TestDto;
import org.hsh.ms.common.ep.controller.BaseCrudController;
import org.hsh.ms.common.ep.facade.CrudFacade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Path("test")
public class TestController extends BaseCrudController<TestDto> {
    @Context
    private UriInfo uriInfo;

    @Inject
    private TestFacade facade;

    @Override
    protected CrudFacade<TestDto> getCrudFacade() {
        return facade;
    }

    @Override
    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    @Override
    protected Long getDtoId(TestDto dto) {
        return dto.getId();
    }
}
