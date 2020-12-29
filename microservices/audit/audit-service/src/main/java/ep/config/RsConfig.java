package ep.config;

import common.ep.mapper.ExceptionMapperHelper;
import ep.controller.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ApplicationPath("api/v1")
public class RsConfig extends Application {
    private final Set<Class<?>> classes;

    public RsConfig() {
        Set<Class<?>> classList = new HashSet<>();

        classList.add(ReadyController.class);
        classList.add(HealthController.class);
        classList.add(MetricsController.class);

        classList.add(DataAuditController.class);
        classList.add(SecurityAuditController.class);
        classList.add(AuditController.class);

        classList.addAll(ExceptionMapperHelper.buildMapperProviderClassList());

        classes = Collections.unmodifiableSet(classList);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return super.getSingletons();
    }

    @Override
    public Map<String, Object> getProperties() {
        return super.getProperties();
    }
}
