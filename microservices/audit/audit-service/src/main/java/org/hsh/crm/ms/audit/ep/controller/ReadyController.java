package org.hsh.crm.ms.audit.ep.controller;

import org.hsh.crm.ms.audit.app.AppInitializer;
import org.hsh.crm.ms.common.ep.CrmEpCommon;
import org.hsh.ms.common.ep.controller.BaseController;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * readyness controller
 *
 * @author elf
 */
@RequestScoped
@Path(CrmEpCommon.RsPath.READY)
public class ReadyController extends BaseController {
    @EJB
    private AppInitializer initializer;

    @GET
    public Response getReadyInfo() {
        if (initializer.isReady())
            return Response.ok().build();

        return buildBadRequest();
    }
}
