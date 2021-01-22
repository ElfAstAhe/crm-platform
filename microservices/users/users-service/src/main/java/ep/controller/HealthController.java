package ep.controller;

import common.ep.controller.BaseSimpleHealthController;

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
