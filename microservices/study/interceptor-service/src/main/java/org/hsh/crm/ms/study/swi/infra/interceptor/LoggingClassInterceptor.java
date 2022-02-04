package org.hsh.crm.ms.study.swi.infra.interceptor;

import org.hsh.crm.ms.study.swi.infra.interceptor.binding.LoggableClass;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@Interceptor
@LoggableClass
public class LoggingClassInterceptor {
    @Inject
    private Logger logger;

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        logger.entering(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        try {
            logger.log(Level.INFO, String.format("processing class method [%s].[%s]", ic.getTarget().getClass().getName(), ic.getMethod().getName()));
            return ic.proceed();
        } finally {
            logger.exiting(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        }
    }
}
