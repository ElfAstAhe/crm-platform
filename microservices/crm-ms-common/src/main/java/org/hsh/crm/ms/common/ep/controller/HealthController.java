package org.hsh.crm.ms.common.ep.controller;

import org.hsh.ms.common.ep.controller.BaseSimpleHealthController;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

/**
 * health controller
 *
 * @author elf
 */
@RequestScoped
@Path("health")
public class HealthController extends BaseSimpleHealthController {
}
