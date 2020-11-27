package common.dal.dao;

import common.dal.entity.IdEntity;
import org.jooq.tools.StringUtils;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.criteria.Join;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DaoUtils {
    private static final String TEMPLATE_PROXY_CLASS_NAME = "%s_";

    public static final String SQL_ASC = "asc";
    public static final String SQL_DESC = "desc";

    private DaoUtils() {
        // hide constructor
    }

    @SuppressWarnings("unchecked")
    public static <Entity extends IdEntity, Key> SingularAttribute<Entity, Key> getEntityIdAttribute(Class<Entity> entityClass) {
        String idFieldName = getEntityIdFieldName(entityClass);
        if (StringUtils.isBlank(idFieldName))
            return null;
        try {
            Class<?> proxyClass = getEntityProxyClass(entityClass);
            if (proxyClass == null)
                return null;
            Field attribute;
            attribute = proxyClass.getField(idFieldName);
            Class<?> type = attribute.getType();
            if (type == SingularAttribute.class) {
                Object value = attribute.get(null);
                if (value instanceof SingularAttribute) {
                    return (SingularAttribute<Entity, Key>) value;
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            // nothing
        }

        return null;
    }

    public static Field getEntityIdField(Class<?> entityClass) {
        try {
            Field[] fields = entityClass.getDeclaredFields();
            for(Field field : fields) {
                if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class))
                    return field;
            }
        } catch (Exception ex) {
            // nothing
        }
        if (entityClass.getSuperclass() == null)
            return null;

        return getEntityIdField(entityClass.getSuperclass());
    }

    public static <Entity extends IdEntity> String getEntityIdFieldName(Class<Entity> entityClass) {
        Field field = getEntityIdField(entityClass);
        return field == null ? null : field.getName();
    }

    public static String getEntityName(Class<?> entityClass) {
        return entityClass == null ? null : entityClass.getSimpleName();
    }

    public static <Entity extends IdEntity> Class<?> getEntityProxyClass(Class<Entity> entityClass) {
        try {
            return Class.forName(String.format(TEMPLATE_PROXY_CLASS_NAME, entityClass.getName()));
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }

    public static List<Map<String, Object>> createFilterSet(Map<String, Object> filters) {
        if (filters == null)
            return new ArrayList<>();

        return Collections.singletonList(filters);
    }

    public static class JoinTable {
        private final Join<?, ?> join;
        private final Class<?> root;
        private final Class<?> target;

        public JoinTable(Join<?, ?> join, Class<?> root, Class<?> target) {
            this.join = join;
            this.root = root;
            this.target = target;
        }

        public Join<?, ?> getJoin() {
            return join;
        }

        @SuppressWarnings("unused")
        public Class<?> getRoot() {
            return root;
        }

        public Class<?> getTarget() {
            return target;
        }
    }
}
