package org.hsh.crm.ms.study.ps.ep.config;

import org.hsh.crm.ms.study.ps.ep.controller.HealthController;
import org.hsh.crm.ms.study.ps.ep.controller.MetricsController;
import org.hsh.crm.ms.study.ps.ep.controller.ReadyController;
import org.hsh.crm.ms.study.ps.ep.controller.TestController;
import org.hsh.ms.common.ep.EpCommon;
import org.hsh.ms.common.ep.mapper.ExceptionMapperHelper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ApplicationPath(EpCommon.RsApi.V1)
public class RsConfig extends Application {
    private final Set<Class<?>> classes;

    public RsConfig() {
        Set<Class<?>> classList = new HashSet<>();

        classList.add(ReadyController.class);
        classList.add(HealthController.class);
        classList.add(MetricsController.class);

        classList.add(TestController.class);

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
