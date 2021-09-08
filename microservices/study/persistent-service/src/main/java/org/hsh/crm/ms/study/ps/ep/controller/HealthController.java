package org.hsh.crm.ms.study.ps.ep.controller;

import org.hsh.crm.ms.common.ep.CrmEpCommon;
import org.hsh.ms.common.ep.controller.BaseSimpleHealthController;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

/**
 * health controller
 *
 * @author elf
 */
@RequestScoped
@Path(CrmEpCommon.RsPath.HEALTH)
public class HealthController extends BaseSimpleHealthController {
}
