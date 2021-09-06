package org.hsh.crm.ms.common.ep.config;

import org.hsh.crm.ms.common.ep.controller.HealthController;
import org.hsh.crm.ms.common.ep.controller.MetricsController;
import org.hsh.ms.common.ep.mapper.ExceptionMapperHelper;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class MsRsConfig extends Application {
    private final Set<Class<?>> classes;

    protected MsRsConfig() {
        classes = new HashSet<>();

        classes.add(HealthController.class);
        classes.add(MetricsController.class);

        classes.addAll(ExceptionMapperHelper.buildMapperProviderClassList());
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
