package common.util.audit;

import common.dal.migration.SqlMigrationHelper;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AuditUtils {
    private static final List<Class<?>> FIELD_ANNOTATIONS = Arrays.asList(FieldInfo.class,
            LinkFieldInfo.class,
            ObjectId.class);
    private static final List<Class<?>> CLASS_ANNOTATIONS = Collections.singletonList(ClassInfo.class);

    private AuditUtils() {
        // hide constructor
    }

    public static boolean isAnyClassAnnotation(Object instance) {
        if (instance == null)
            return false;

        return isAnyAnnotation(CLASS_ANNOTATIONS, instance.getClass().getAnnotations());
    }

    public static boolean isAnyFieldAnnotation(Field field) {
        if (field == null)
            return false;

        return isAnyAnnotation(FIELD_ANNOTATIONS, field.getAnnotations());
    }

    public static boolean isObjectId(Field field) {
        if (field == null)
            return false;

        return isAnnotated(field, ObjectId.class);
    }

    public static boolean isFieldInfo(Field field) {
        if (field == null)
            return false;

        return isAnnotated(field, FieldInfo.class);
    }

    public static boolean isLinkFieldInfo(Field field) {
        if (field == null)
            return false;

        return isAnnotated(field, LinkFieldInfo.class);
    }

    public static boolean isClassInfo(Object instance) {
        if (instance == null)
            return false;

        return isAnnotated(instance.getClass(), ClassInfo.class);
    }

    public static String classDescription(Object instance) {
        if (!isClassInfo(instance))
            return null;

        return getClassDescriptionInternal(instance.getClass());
    }

    public static String linkClassDescription(Class<?> linkClass) {
        if (linkClass == null || !isAnnotated(linkClass, ClassInfo.class)) {
            return null;
        }

        return getClassDescriptionInternal(linkClass);
    }

    public static String fieldDescription(Field field) {
        if (isFieldInfo(field)) {
            return field.getAnnotation(FieldInfo.class)
                    .value();
        }
        if (isLinkFieldInfo(field)) {
            String description = field.getAnnotation(LinkFieldInfo.class)
                    .value();
            if (StringUtils.isBlank(description)) {
                description = linkClassDescription(linkFieldClass(field));
            }

            return description;
        }

        return null;
    }

    public static Class<?> linkFieldClass(Field field) {
        if (!isLinkFieldInfo(field))
            return null;

        return field.getAnnotation(LinkFieldInfo.class)
                .linkClass();
    }

    public static List<Field> buildAllAnnotatedFieldList(Object instance) {
        if (instance == null)
            return Collections.emptyList();

        return buildAllFieldListRecursive(instance.getClass()).stream()
                .filter(AuditUtils::isAnyFieldAnnotation)
                .collect(Collectors.toList());
    }

    public static String objectIdValue(Object instance) {
        List<Field> fields = buildAllAnnotatedFieldList(instance);
        Field fieldObjectId = fields.stream()
                .filter(AuditUtils::isObjectId)
                .findAny()
                .orElse(null);

        return valueOrNull(instance, fieldObjectId);
    }

    public static String valueOrNull(Object instance, Field field) {
        if (instance == null || field == null)
            return null;

        try {
            field.setAccessible(true);
            Object result = field.get(instance);
            return result == null ? null : String.valueOf(result);
        } catch (IllegalAccessException ex) {
            return null;
        }
    }

    private static List<Field> buildAllFieldListRecursive(Class<?> instanceClass) {
        // stop
        if (instanceClass == null || !isAnnotated(instanceClass, ClassInfo.class)) {
            return new ArrayList<>();
        }

        List<Field> classFields = new ArrayList<>(Arrays.asList(instanceClass.getDeclaredFields()));

        if (instanceClass.getSuperclass() != null) {
            classFields.addAll(buildAllFieldListRecursive(instanceClass.getSuperclass()));
        }

        return classFields;
    }

    private static boolean isAnyAnnotation(List<Class<?>> annotationClasses, Annotation... annotations) {
        return Arrays.stream(annotations)
                .map(Annotation::annotationType)
                .anyMatch(annotationClasses::contains);
    }

    private static <T extends Annotation> boolean isAnnotated(AnnotatedElement element, Class<T> annotationClass) {
        return element.getAnnotation(annotationClass) != null;
    }

    private static String getClassDescriptionInternal(Class<?> instanceClass) {
        return instanceClass.getAnnotation(ClassInfo.class)
                .value();
    }
}
