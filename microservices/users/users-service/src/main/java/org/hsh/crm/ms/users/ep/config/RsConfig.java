package org.hsh.crm.ms.users.ep.config;

import org.hsh.crm.ms.common.ep.config.MsRsConfig;
import org.hsh.crm.ms.users.ep.controller.ReadyController;
import org.hsh.crm.ms.users.ep.controller.RoleController;
import org.hsh.crm.ms.users.ep.controller.UserController;
import org.hsh.ms.common.ep.EpCommon;

import javax.ws.rs.ApplicationPath;

@ApplicationPath(EpCommon.RsApi.V1)
public class RsConfig extends MsRsConfig {
    public RsConfig() {
        super();

        getClasses().add(ReadyController.class);
        getClasses().add(UserController.class);
        getClasses().add(RoleController.class);
    }
}
