package common.ep.controller;

import common.dto.helper.ExceptionDtoHelper;
import common.ep.facade.BaseCrudFacade;
import common.web.MimeTypes;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Базовый класс контроллера
 *
 * @author elf
 */
public abstract class BaseCrudController<TDto, TKey> {

    @GET
    @Path("{id}")
    @Produces({MimeTypes.Application.JSON, MimeTypes.Application.XML})
    public Response getInstance(
            @PathParam("id") String id) {
        try {
            // Параметры
            long _id;
            try {
                _id = Long.parseUnsignedLong(id);
            } catch (NumberFormatException ex) {
//                return Response.status(Response.Status.BAD_REQUEST).build();
                throw new BadRequestException();
            }

            // Обработка
            TDto result = getCrudFacade().getInstance(_id);

            // Не нашли
            if (result == null)
                return Response.noContent().build();

            return Response.ok(result).build();
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Throwable ex) {
            return Response
                    .serverError()
                    .entity(ExceptionDtoHelper.toDto(ex))
                    .build();
        }
    }

    @GET
    @Path("list/all")
    @Produces({MimeTypes.Application.JSON, MimeTypes.Application.XML})
    public Response listAllInstances() {
        try {
            return Response
                    .ok(getGenericEntity(getCrudFacade().listAllInstances()))
                    .build();
        } catch (Throwable ex) {
            return Response
                    .serverError()
                    .entity(ExceptionDtoHelper.toDto(ex))
                    .build();
        }
    }

    @GET
    @Path("list")
    @Produces({MimeTypes.Application.JSON, MimeTypes.Application.XML})
    public Response list(
            @QueryParam("fromRow") @DefaultValue(value = "0") String fromRow,
            @QueryParam("rowCount") @DefaultValue(value = "100") String rowCount
    ) {
        try {
            // Параметры
            int paramFromRow;
            int paramRowCount;
            try {
                paramFromRow = Integer.parseInt(fromRow);
                paramRowCount = Integer.parseInt(rowCount);
            } catch (NumberFormatException ex) {
//                return Response.status(Response.Status.BAD_REQUEST).build();
                throw new BadRequestException();
            }

            return Response
                    .ok(getGenericEntity(getCrudFacade().listInstances(paramFromRow, paramRowCount)))
                    .build();
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Throwable ex) {
            return Response
                    .serverError()
                    .entity(ExceptionDtoHelper.toDto(ex))
                    .build();
        }
    }

    @POST
    @Consumes({MimeTypes.Application.JSON, MimeTypes.Application.XML})
    @Produces({MimeTypes.Application.JSON, MimeTypes.Application.XML})
    public Response createInstance(TDto instance) {
        try {
            TDto result = getCrudFacade().createInstance(instance);

            URI uri = getUriInfo()
                    .getAbsolutePathBuilder()
                    .path(getDtoId(result).toString())
                    .build((Object) null);

            return Response.created(uri).entity(result).build();
        } catch (Throwable ex) {
            return Response
                    .serverError()
                    .entity(ExceptionDtoHelper.toDto(ex))
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MimeTypes.Application.JSON, MimeTypes.Application.XML})
    @Produces({MimeTypes.Application.JSON, MimeTypes.Application.XML})
    public Response editInstance(
            @PathParam("id") String id, TDto instance) {
        try {
            // Параметры
            if (instance == null || StringUtils.isBlank(id)) {
                throw new BadRequestException();
            }

            return Response.ok(getCrudFacade().editInstance(getDtoId(instance), instance)).build();
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Throwable ex) {
            return Response
                    .serverError()
                    .entity(ExceptionDtoHelper.toDto(ex))
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces({MimeTypes.Application.JSON, MimeTypes.Application.XML})
    public Response removeInstance(
            @PathParam("id") String id) {
        try {
            // Параметры
            long paramId;
            try {
                paramId = Long.parseUnsignedLong(id);
            } catch (NumberFormatException ex) {
//                return Response.status(Response.Status.BAD_REQUEST).build();
                throw new BadRequestException();
            }

            // Удаление
            getCrudFacade().removeInstance(paramId);
            return Response.noContent().build();
        } catch (WebApplicationException ex) {
            throw ex;
        } catch (Throwable ex) {
            return Response
                    .serverError()
                    .entity(ExceptionDtoHelper.toDto(ex))
                    .build();
        }
    }

    protected Response getInstanceByKeySimpleFunc(String key) {
        try {
            // Обработка
            TDto result = getCrudFacade().getInstanceByKey(toFacadeKey(key));

            // Не нашли
            if (result == null)
                return Response.noContent().build();

            return Response.ok(result).build();
        } catch (Throwable ex) {
            return Response
                    .serverError()
                    .entity(ExceptionDtoHelper.toDto(ex))
                    .build();
        }
    }

    protected abstract BaseCrudFacade<TDto, TKey> getCrudFacade();

    protected abstract UriInfo getUriInfo();

    protected abstract TKey toFacadeKey(Object... key);

    protected abstract Long getDtoId(TDto instance);

    protected abstract GenericEntity<List<TDto>> getGenericEntity(List<TDto> list);
}
