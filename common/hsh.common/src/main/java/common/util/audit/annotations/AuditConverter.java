package common.util.audit.annotations;

import common.util.audit.AuditValueConverter;
import common.util.audit.DefaultAuditValueConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface AuditConverter {
    Class<? extends AuditValueConverter> converterClass() default DefaultAuditValueConverter.class;
}
