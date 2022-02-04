package org.hsh.crm.ms.study.swi.ep.controller;

import org.hsh.crm.ms.study.swi.infra.interceptor.LoggingClassInterceptor;
import org.hsh.ms.common.infra.interceptors.DurationProfileInterceptor;
import org.hsh.ms.common.web.MimeTypes;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@RequestScoped
@Named
@Path("test")
public class TestController {
    @Inject
    private TestFacade facade;

    @GET
    @Produces({MimeTypes.Application.JSON})
    @Interceptors({DurationProfileInterceptor.class, LoggingClassInterceptor.class})
    public Response test() {
        return Response.ok().build();
    }

    @GET
    @Path("hello")
    @Produces({MimeTypes.Application.JSON})
    public Response sayHello() {
        return Response.ok(facade.sayHello()).build();
    }

    @GET
    @Path("hello/{person}")
    @Produces({MimeTypes.Application.JSON})
    public Response sayHelloPerson(@PathParam("person") @NotNull String person) {
        return Response.ok(facade.sayPersonalHello(person)).build();
    }

    @GET
    @Path("hello2")
    @Produces({MimeTypes.Application.JSON})
    public Response sayHello2() {
        return Response.ok(facade.sayHello2()).build();
    }
}
