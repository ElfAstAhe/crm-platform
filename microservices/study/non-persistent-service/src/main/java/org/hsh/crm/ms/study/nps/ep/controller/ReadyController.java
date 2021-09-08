package org.hsh.crm.ms.study.nps.ep.controller;

import org.hsh.common.app.AppInitializer;
import org.hsh.crm.ms.common.ep.CrmEpCommon;

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
public class ReadyController {

    @EJB
    private AppInitializer initializer;

    @GET
    public Response getReadyInfo() {
        if (initializer.isReady())
            return Response.ok().build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
