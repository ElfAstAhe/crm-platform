package common.dal.dao;

import common.dal.entity.IdEntity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * dao helper
 *
 * @param <Entity>
 */
public class DaoHelper<Entity extends IdEntity> {
    private final Class<Entity> entityClass;
    private final boolean usePath;

    public DaoHelper(Class<Entity> entityClass, boolean isUsePath) {
        this.entityClass = entityClass;
        this.usePath = isUsePath;
    }

    public Predicate createSingleCondition(CriteriaBuilder cb,
                                           Map<String, Object> filters,
                                           Root<Entity> root,
                                           List<DaoUtils.JoinTable> joins) {
        Predicate[] and = filters.entrySet()
                .stream()
                .map(f -> {
                    String key = f.getKey();
                    Object value = f.getValue();
                    if (!Objects.equals(value, StringUtils.EMPTY)) {
                        Path<?> pathFilter = null;  // <==== getPathInternal
                        Predicate customPredicate = createCustomPredicate(cb, pathFilter, root, joins, key, value);
                        if (customPredicate != null)
                            return customPredicate;

                        switch (FilterClassTypeEnum.classToEnum(value)) {
                            case BOOLEAN_ARR:
                                List<Boolean> bools = Arrays.asList((Boolean[]) value);
                                return CriteriaConditionBuilder.buildList(cb, key, pathFilter, bools);
                            case INTEGER_ARR:
                                List<Integer> ints = Arrays.asList((Integer[]) value);
                                return CriteriaConditionBuilder.buildList(cb, key, pathFilter, ints);
                            case LONG_ARR:
                                List<Long> longs = Arrays.asList((Long[]) value);
                                return CriteriaConditionBuilder.buildList(cb, key, pathFilter, longs);
                            case DOUBLE_ARR:
                                List<Double> doubles = Arrays.asList((Double[]) value);
                                return CriteriaConditionBuilder.buildList(cb, key, pathFilter, doubles);
                            case BIG_DECIMAL_ARR:
                                List<BigDecimal> bigDecimals = Arrays.asList((BigDecimal[]) value);
                                return CriteriaConditionBuilder.buildList(cb, key, pathFilter, bigDecimals);
                            case STRING_ARR:
                                List<String> strings = Arrays.asList((String[]) value);
                                return CriteriaConditionBuilder.buildList(cb, key, pathFilter, strings);
                            case OBJECT_ARR:
                                List<Object> objects = Arrays.asList((Object[]) value);
                                return CriteriaConditionBuilder.buildList(cb, key, pathFilter, objects);
                            case INTEGER:
                            case LONG:
                            case DOUBLE:
                            case BIG_DECIMAL:
                                return CriteriaConditionBuilder.buildDigit(cb, key, pathFilter, (Number) value);
                            case STRING:
                                return CriteriaConditionBuilder.buildString(cb, key, pathFilter, (String) value);
                            case ENUM:
                            default:
                                return CriteriaConditionBuilder.buildCommon(cb, key, pathFilter, value);
                        }
                    } else if (StringUtils.endsWith(key, "<>") && value instanceof String) {
                        Path<?> pathFilter = null;  // <==== getPathInternal

                        return CriteriaConditionBuilder.buildString(cb, key, pathFilter, (String) value);
                    }

                    return null;
                })
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new);

        if (and.length == 0)
            return cb.conjunction();

        return cb.and(and);
    }

    /**
     * for predicate customization in ancestor classes
     *
     * @param cb criteria builder
     * @param pathFilter path
     * @param root root
     * @param joins joins
     * @param name field name
     * @param value field value
     * @return custom predicate
     */
    protected Predicate createCustomPredicate(CriteriaBuilder cb,
                                              Path<?> pathFilter,
                                              Root<Entity> root,
                                              List<DaoUtils.JoinTable> joins,
                                              String name,
                                              Object value) {
        return null;
    }

    /**
     * for overriding
     *
     * @return joins
     */
    public List<DaoUtils.JoinTable> getJoins(Root<Entity> root) {
        return new ArrayList<>();
    }

    protected Predicate createWhere(CriteriaBuilder cb, Root<Entity> root, List<DaoUtils.JoinTable> joins, List<Map<String, Object>> filterSet) {
        if (filterSet == null || filterSet.isEmpty()) {
            return cb.conjunction();
        }

        return cb.or(filterSet.stream()
                .map(filters -> createSingleCondition(cb, filters, root, joins))
                .toArray(Predicate[]::new));
    }

    protected Class<Entity> getEntityClass() {
        return entityClass;
    }

    protected boolean isUsePath() {
        return usePath;
    }

    /**
     * for overriding
     *
     * @param root root
     * @param joins joins
     * @param field field
     * @return path
     */
    protected Path<?> createCustomPath(Root<Entity> root, List<DaoUtils.JoinTable> joins, String field) {
        return null;
    }

    public Path<?> createPathConditioning(Root<Entity> root, List<DaoUtils.JoinTable> joins, String field) {
        if (StringUtils.isNotEmpty(field)) {
            if (field.endsWith(CriteriaConditionBuilder.CONDITION_NOT_EQUAL))
                field = field.replace(CriteriaConditionBuilder.CONDITION_NOT_EQUAL, StringUtils.EMPTY);
            else if (field.endsWith(CriteriaConditionBuilder.CONDITION_MORE_EQUAL))
                field = field.replace(CriteriaConditionBuilder.CONDITION_MORE_EQUAL, StringUtils.EMPTY);
            else if (field.endsWith(CriteriaConditionBuilder.CONDITION_MORE))
                field = field.replace(CriteriaConditionBuilder.CONDITION_MORE, StringUtils.EMPTY);
            else if (field.endsWith(CriteriaConditionBuilder.CONDITION_LESS))
                field = field.replace(CriteriaConditionBuilder.CONDITION_LESS, StringUtils.EMPTY);
            else if (field.endsWith(CriteriaConditionBuilder.CONDITION_LESS_EQUAL))
                field = field.replace(CriteriaConditionBuilder.CONDITION_LESS_EQUAL, StringUtils.EMPTY);
            else if (field.endsWith(CriteriaConditionBuilder.CONDITION_EQUAL))
                field = field.replace(CriteriaConditionBuilder.CONDITION_EQUAL, StringUtils.EMPTY);
        }

        return usePath ? createPath(root, joins, field) : root.get(field);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Path<?> createPath(Root<Entity> root, List<DaoUtils.JoinTable> joins, String field) {
        Path<?> path = createCustomPath(root, joins, field);
        if (path != null) {
            return path;
        }

        try {
            Class<?> metaData = Class.forName(entityClass.getName() + "_", true, entityClass.getClassLoader());
            Field attribute = metaData.getField(field);
            Class<?> type = attribute.getType();
            if (type == SingularAttribute.class) {
                Object value = attribute.get(null);
                if (value instanceof SingularAttribute) {
                    return root.get((SingularAttribute) value);
                }
            }
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }

        return root.get(DaoUtils.getEntityIdField(entityClass));
    }
}
