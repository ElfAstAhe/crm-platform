package org.hsh.crm.ms.audit.ep.config;

import org.hsh.crm.ms.audit.ep.controller.*;
import org.hsh.crm.ms.common.ep.config.MsRsConfig;
import org.hsh.ms.common.ep.EpCommon;

import javax.ws.rs.ApplicationPath;

@ApplicationPath(EpCommon.RsApi.V1)
public class RsConfig extends MsRsConfig {
    public RsConfig() {
        getClasses().add(ReadyController.class);

        getClasses().add(DataAuditController.class);
        getClasses().add(SecurityAuditController.class);
        getClasses().add(AuditController.class);
    }
}
