package org.hsh.crm.ms.study.swi.ep.controller;

import javax.ejb.Stateless;

@Stateless
public class TestFacadeImpl extends BaseTestFacadeImpl implements TestFacade {
    @Override
    public String sayPersonalHello(String person) {
        return "hello " + person;
    }

    @Override
    public String sayHello2() {
        return "hello2";
    }
}
