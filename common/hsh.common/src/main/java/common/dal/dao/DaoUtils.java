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
    private DaoUtils() {
        // hide constructor
    }

    @SuppressWarnings("unchecked")
    public static <Entity extends IdEntity, Key> SingularAttribute<Entity, Key> getEntityIdField(Class<Entity> entityClass) {
        String idFieldName = getEntityIdFieldName(entityClass);
        if (StringUtils.isBlank(idFieldName))
            return null;
        try {
            Class<?> proxyClass = getEntityProxyClass(entityClass);
            if (proxyClass == null)
                return null;
            Field attribute = null;
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

    public static <Entity extends IdEntity> String getEntityIdFieldName(Class<Entity> entityClass) {
        try {
            Field[] fields = entityClass.getDeclaredFields();
            for(Field field : fields) {
                EmbeddedId[] embeddedIds = field.getAnnotationsByType(EmbeddedId.class);
                if (embeddedIds.length > 0) {
                    return field.getName();
                }

                Id[] ids = field.getAnnotationsByType(Id.class);
                if (ids.length > 0) {
                    return field.getName();
                }
            }
        } catch (SecurityException ex) {
            // nothing
        }

        return null;
    }

    public static <Entity extends IdEntity> Class<?> getEntityProxyClass(Class<Entity> entityClass) {
        try {
            return Class.forName(entityClass.getName() + "_");
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

        public Class<?> getRoot() {
            return root;
        }

        public Class<?> getTarget() {
            return target;
        }
    }
}
