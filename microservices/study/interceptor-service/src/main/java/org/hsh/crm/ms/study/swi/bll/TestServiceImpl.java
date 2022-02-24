package org.hsh.crm.ms.study.swi.bll;

import org.hsh.crm.ms.study.swi.infra.interceptor.binding.LoggableClass;

import javax.ejb.Stateless;

@Stateless
public class TestServiceImpl implements TestService{

    @LoggableClass
    @Override
    public String sayHello() {
        return "hello there";
    }
}
