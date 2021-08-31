package org.hsh.crm.ms.audit.ep.controller;

import org.hsh.crm.ms.audit.dto.Audit;
import org.hsh.crm.ms.audit.ep.controller.facade.AuditFacade;
import org.hsh.ms.common.dto.ExceptionDtoHelper;
import org.hsh.ms.common.web.MimeTypes;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Контроллер отчётов
 */
@Path("audit")
public class AuditController {
    @Inject
    private AuditFacade facade;

    @Path("list/all")
    @GET
    @Produces({MimeTypes.Application.JSON, MimeTypes.Application.XML, MimeTypes.Application.YAML})
    public Response listAll() {
        try {
            return Response.ok(new GenericEntity<List<Audit>>(facade.listAll()){})
                    .build();
        } catch (WebApplicationException ex) {
                throw ex;
        } catch (Exception ex) {
            return Response.serverError()
                    .entity(ExceptionDtoHelper.toDto(ex))
                    .build();
        }
    }
}
