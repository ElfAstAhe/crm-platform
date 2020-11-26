package common.util.audit;

import common.util.audit.annotations.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class AuditUtils {
    private static final String TEMPLATE_FIELD_VALUE_CREDENTIAL = "[censored::%s]";

    private static final List<Class<?>> ANNOTATED_ELEMENT_ANNOTATIONS = Arrays.asList(AuditFieldInfo.class,
            AuditLinkFieldInfo.class,
            AuditTransient.class,
            AuditObjectId.class,
            AuditObjectName.class,
            AuditCredential.class);
    private static final List<Class<?>> ANNOTATED_ELEMENT_IGNORE_ANNOTATIONS = Arrays.asList(AuditTransient.class,
            AuditObjectName.class);
    private static final List<Class<?>> CLASS_ANNOTATIONS = Collections.singletonList(AuditClassInfo.class);

    private AuditUtils() {
        // hide constructor
    }

    public static boolean isAnyClassAnnotations(Object instance) {
        if (instance == null)
            return false;

        return isAnyAnnotations(CLASS_ANNOTATIONS, instance.getClass().getAnnotations());
    }

    public static boolean isIgnore(AnnotatedElement element) {
        return isAnyAnnotations(ANNOTATED_ELEMENT_IGNORE_ANNOTATIONS, element.getAnnotations());
    }

    public static boolean isNonIgnore(AnnotatedElement element) {
        return !isIgnore(element);
    }

    public static boolean isHasAnnotations(AnnotatedElement element) {
        if (element == null)
            return false;

        return isAnyAnnotations(ANNOTATED_ELEMENT_ANNOTATIONS, element.getAnnotations());
    }

    public static boolean isObjectId(Field field) {
        return isAnnotated(field, AuditObjectId.class);
    }

    public static boolean isFieldInfo(AnnotatedElement element) {
        return isAnnotated(element, AuditFieldInfo.class);
    }

    public static boolean isCredentialInfo(AnnotatedElement element) {
        return isAnnotated(element, AuditCredential.class);
    }

    public static boolean isLinkFieldInfo(AnnotatedElement element) {
        return isAnnotated(element, AuditLinkFieldInfo.class);
    }

    public static boolean isClassInfo(Object instance) {
        return isAnnotated(instance.getClass(), AuditClassInfo.class);
    }

    public static String classDescription(Object instance) {
        if (!isClassInfo(instance))
            return null;

        return getClassDescriptionInternal(instance.getClass());
    }

    public static String linkClassDescription(Class<?> linkClass) {
        if (!isAnnotated(linkClass, AuditClassInfo.class))
            return null;

        return getClassDescriptionInternal(linkClass);
    }

    public static String fieldDescription(AnnotatedElement element) {
        if (isFieldInfo(element))
            return element.getAnnotation(AuditFieldInfo.class).value();
        if (!isLinkFieldInfo(element))
            return null;

        String description = element.getAnnotation(AuditLinkFieldInfo.class).value();
        if (StringUtils.isBlank(description))
            description = linkClassDescription(linkFieldClass(element));

        return description;
    }

    public static Class<?> linkFieldClass(AnnotatedElement element) {
        if (!isLinkFieldInfo(element))
            return null;

        return element.getAnnotation(AuditLinkFieldInfo.class)
                .linkClass();
    }

    public static List<AnnotatedElement> buildAllAnnotatedElementList(Object instance) {
        if (instance == null)
            return Collections.emptyList();

        return buildAllAnnotatedElementListRecursive(instance.getClass()).stream()
                .filter(AuditUtils::isHasAnnotations)
                .filter(AuditUtils::isNonIgnore)
                .collect(Collectors.toList());
    }

    public static <T extends Annotation> AnnotatedElement findAnnotatedElement(Object instance, Class<T> annotationClass) {
        if (instance == null || annotationClass == null)
            return null;

        return findAnnotatedElementRecursive(instance.getClass(), annotationClass);
    }

    public static String objectIdValue(Object instance) {
        return valueOrNull(instance, findAnnotatedElement(instance, AuditObjectId.class));
    }

    public static String objectNameValue(Object instance) {
        return valueOrNull(instance, findAnnotatedElement(instance, AuditObjectName.class));
    }

    public static String valueOrNull(Object instance, AnnotatedElement element) {
        if (instance == null || element == null)
            return null;

        Object result = elementValue(instance, element);
        if (result != null && isCredentialInfo(element))
            result = maskCredential(result);
        return result == null ? null : String.valueOf(result);
    }

    public static String maskCredential(Object value) {
        return String.format(TEMPLATE_FIELD_VALUE_CREDENTIAL, DigestUtils.sha256Hex(String.valueOf(value)));
    }

    public static String elementName(AnnotatedElement element) {
        if (element instanceof Field) {
            return ((Field) element).getName();
        } else if (element instanceof Method) {
            return ((Method) element).getName();
        }

        return null;
    }

    private static Object elementValue(Object instance, AnnotatedElement element) {
        if (element instanceof Field) {
            return getFieldValue(instance, (Field) element);
        } else if (element instanceof Method) {
            return getMethodValue(instance, (Method) element);
        }

        return null;
    }

    private static Object getFieldValue(Object instance, Field field) {
        if (instance == null || field == null)
            return null;

        try {
            field.setAccessible(true);
            Object value = field.get(instance);
            if (value != null && isAnnotated(field, AuditConverter.class)) {
                value = convertValue(field.getAnnotation(AuditConverter.class).converterClass(), value);
            }
            return value;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    private static Object getMethodValue(Object instance, Method method) {
        if (instance == null || method == null)
            return null;

        try {
            method.setAccessible(true);
            return method.invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            return null;
        }
    }

    private static Object convertValue(Class<? extends AuditValueConverter> converterClass, Object value) {
        try {
            AuditValueConverter converter = converterClass.newInstance();
            return converter.toAuditValue(value);
        } catch (Exception ex) {
            return value;
        }
    }

    private static List<AnnotatedElement> buildAllAnnotatedElementListRecursive(Class<?> instanceClass) {
        // stop
        if (!isAnnotated(instanceClass, AuditClassInfo.class)) {
            return new ArrayList<>();
        }

        List<AnnotatedElement> classAnnotatedElements = new ArrayList<>(Arrays.asList(instanceClass.getDeclaredFields()));
        classAnnotatedElements.addAll(Arrays.asList(instanceClass.getDeclaredFields()));

        if (instanceClass.getSuperclass() != null) {
            classAnnotatedElements.addAll(buildAllAnnotatedElementListRecursive(instanceClass.getSuperclass()));
        }

        return classAnnotatedElements;
    }

    private static <T extends Annotation> AnnotatedElement findAnnotatedElementRecursive(Class<?> instanceClass, Class<T> annotationClass) {
        if(!isAnnotated(instanceClass, AuditClassInfo.class))
            return null;

        AnnotatedElement element = Arrays.stream(instanceClass.getDeclaredFields())
                .filter(f -> AuditUtils.isAnnotated(f, annotationClass))
                .findFirst()
                .orElse(null);
        if (element == null)
            element = Arrays.stream(instanceClass.getDeclaredMethods())
                    .filter(m -> AuditUtils.isAnnotated(m, annotationClass))
                    .findFirst()
                    .orElse(null);
        if (element == null && instanceClass.getSuperclass() != null)
            element = findAnnotatedElementRecursive(instanceClass.getSuperclass(), annotationClass);

        return element;
    }

    private static boolean isAnyAnnotations(List<Class<?>> annotationClasses, Annotation... annotations) {
        return Arrays.stream(annotations)
                .map(Annotation::annotationType)
                .anyMatch(annotationClasses::contains);
    }

    private static <T extends Annotation> boolean isAnnotated(AnnotatedElement element, Class<T> annotationClass) {
        if (element == null)
            return false;

        return element.getAnnotation(annotationClass) != null;
    }

    private static String getClassDescriptionInternal(Class<?> instanceClass) {
        return instanceClass.getAnnotation(AuditClassInfo.class)
                .value();
    }
}
