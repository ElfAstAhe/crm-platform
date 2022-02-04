package org.hsh.crm.ms.study.swi.ep.controller;

import org.hsh.ms.common.infra.interceptors.DurationProfileInterceptor;

import javax.interceptor.Interceptors;

public abstract class BaseTestFacadeImpl implements TestFacade {
    @Interceptors({DurationProfileInterceptor.class})
    @Override
    public String sayHello() {
        return "hello";
    }
}
