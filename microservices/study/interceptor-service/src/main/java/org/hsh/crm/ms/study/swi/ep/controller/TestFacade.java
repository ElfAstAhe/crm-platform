package org.hsh.crm.ms.study.swi.ep.controller;

import org.hsh.ms.common.infra.interceptors.DurationProfileInterceptor;
import org.hsh.ms.common.infra.interceptors.binding.DurationProfile;

import javax.ejb.Local;
import javax.interceptor.Interceptors;

@Local
public interface TestFacade extends BaseTestFacade {
    @DurationProfile
    String sayPersonalHello(String person);

    @Interceptors({DurationProfileInterceptor.class})
    String sayHello2();

    String sayHello3();
}
