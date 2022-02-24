package org.hsh.crm.ms.study.swi.ep.controller;

import org.hsh.crm.ms.study.swi.bll.TestService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TestFacadeImpl extends BaseTestFacadeImpl implements TestFacade {
    @EJB
    private TestService sTest;

    @Override
    public String sayPersonalHello(String person) {
        return "hello " + person;
    }

    @Override
    public String sayHello2() {
        return "hello2";
    }

    @Override
    public String sayHello3() {
        return sTest.sayHello();
    }
}
