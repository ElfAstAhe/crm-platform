package ep.controller;

import common.ep.controller.BasePayaraMetricsController;
import common.web.MimeTypes;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.concurrent.ExecutorService;

/**
 * simple metrics controller
 *
 * @author elf
 */
@RequestScoped
@Path("metrics")
@Produces({MimeTypes.Text.PLAIN})
public class MetricsController extends BasePayaraMetricsController {
    @Context
    private HttpServletRequest request;

    @Resource
    private ManagedExecutorService executor;

    @Override
    protected HttpServletRequest getRequest() {
        return request;
    }

    @Override
    protected ExecutorService getExecutor() {
        return executor;
    }
}
