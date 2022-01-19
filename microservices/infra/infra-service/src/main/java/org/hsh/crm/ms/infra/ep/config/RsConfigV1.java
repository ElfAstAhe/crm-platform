package org.hsh.crm.ms.infra.ep.config;

import org.hsh.crm.ms.common.ep.config.MsRsConfig;
import org.hsh.crm.ms.infra.ep.controller.ReadyController;
import org.hsh.ms.common.ep.EpCommon;

import javax.ws.rs.ApplicationPath;

@ApplicationPath(EpCommon.RsApi.V1)
public class RsConfigV1 extends MsRsConfig {
    public RsConfigV1() {
        getClasses().add(ReadyController.class);

    }
}
