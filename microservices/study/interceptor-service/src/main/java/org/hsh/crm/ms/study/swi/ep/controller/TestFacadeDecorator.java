package org.hsh.crm.ms.study.swi.ep.controller;

import org.hsh.ms.common.infra.interceptors.DurationProfileInterceptor;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.logging.Logger;

@Decorator
public class TestFacadeDecorator implements TestFacade {
    @Inject
    @Delegate
    private TestFacade facade;

    @Inject
    private Logger logger;

    @Override
    @Interceptors({DurationProfileInterceptor.class})
    public String sayHello() {
        return facade.sayHello();
    }

    @Override
    @Interceptors({DurationProfileInterceptor.class})
    public String sayPersonalHello(String person) {
        return facade.sayPersonalHello(person);
    }

    @Override
    @Interceptors({DurationProfileInterceptor.class})
    public String sayHello2() {
        return "hello3";
    }
}
