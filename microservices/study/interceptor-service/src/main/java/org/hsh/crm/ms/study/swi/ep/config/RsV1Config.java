package org.hsh.crm.ms.study.swi.ep.config;

import org.hsh.crm.ms.common.ep.config.MsRsConfig;
import org.hsh.crm.ms.study.swi.ep.controller.ReadyController;
import org.hsh.crm.ms.study.swi.ep.controller.TestController;
import org.hsh.ms.common.ep.EpCommon;

import javax.ws.rs.ApplicationPath;

@ApplicationPath(EpCommon.RsApi.V1)
public class RsV1Config extends MsRsConfig {
    public RsV1Config() {
        super();

        getClasses().add(ReadyController.class);
        getClasses().add(TestController.class);
    }
}
