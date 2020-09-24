package common.dal.dao;

import org.jooq.tools.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

public class CriteriaConditionBuilder {
    public static final String CONDITION_NOT_EQUAL = "<>";
    public static final String CONDITION_EQUAL = "=";
    public static final String CONDITION_MORE_EQUAL = ">=";
    public static final String CONDITION_MORE = ">";
    public static final String CONDITION_LESS = "<";
    public static final String CONDITION_LESS_EQUAL = "<=";

    private CriteriaConditionBuilder() {
        // hide constructor
    }

    public static Predicate buildList(CriteriaBuilder cb, String key, Path<?> pathFilter, List<?> inParams) {
        if (inParams.isEmpty()) {
            return cb.isNull(pathFilter);
        } else {
            return key.endsWith(CONDITION_NOT_EQUAL) ? cb.not(pathFilter.in(inParams)) : pathFilter.in(inParams);
        }
    }

    public static Predicate buildCommon(CriteriaBuilder cb, String key, Path<?> pathFilter, Object value) {
        if (key.endsWith(CONDITION_NOT_EQUAL)) {
            return cb.notEqual(pathFilter, value);
        }

        return cb.equal(pathFilter, value);
    }

    @SuppressWarnings("unchecked")
    public static Predicate buildDate(CriteriaBuilder cb, String key, Path<?> pathFilter, Date value) {
        if (key.endsWith(CONDITION_NOT_EQUAL))
            return cb.notEqual(pathFilter, value);
        else if (key.endsWith(CONDITION_LESS_EQUAL))
            return cb.lessThanOrEqualTo((Path<Date>) pathFilter, value);
        else if (key.endsWith(CONDITION_MORE))
            return cb.greaterThanOrEqualTo((Path<Date>)pathFilter, value);

        return cb.equal(pathFilter, value);
    }

    @SuppressWarnings("unchecked")
    public static Predicate buildOffsetDateTime(CriteriaBuilder cb, String key, Path<?> pathFilter, OffsetDateTime value) {
        if (key.endsWith(CONDITION_NOT_EQUAL))
            return cb.notEqual(pathFilter, value);
        else if (key.endsWith(CONDITION_LESS))
            return cb.lessThanOrEqualTo((Path< OffsetDateTime>) pathFilter, value);
        else if (key.endsWith(CONDITION_MORE))
            return cb.greaterThanOrEqualTo((Path<OffsetDateTime>)pathFilter, value);

        return cb.equal(pathFilter, value);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> Predicate buildDigit(CriteriaBuilder cb, String key, Path<?> pathFilter, T value) {
        if (key.endsWith(CONDITION_NOT_EQUAL))
            return cb.notEqual(pathFilter, value);
        else if (key.endsWith(CONDITION_LESS_EQUAL))
            return cb.le((Path<T>) pathFilter, value);
        else if (key.endsWith(CONDITION_LESS))
            return cb.lt((Path<T>)pathFilter, value);
        else if (key.endsWith(CONDITION_MORE_EQUAL))
            return cb.ge((Path<T>) pathFilter, value);
        else if (key.endsWith(CONDITION_MORE))
            return cb.gt((Path<T>) pathFilter, value);

        return cb.equal(pathFilter, value);
    }

    @SuppressWarnings("unchecked")
    public static Predicate buildString(CriteriaBuilder cb, String key, Path<?> pathFilter, String value) {
        if (key.endsWith(CONDITION_NOT_EQUAL)) {
            if (StringUtils.isBlank(value)) {
                return cb.and(cb.notEqual(pathFilter, StringUtils.EMPTY), cb.isNotNull(pathFilter));
            } else {
                return cb.notEqual(pathFilter, value);
            }
        } else if (key.endsWith(CONDITION_EQUAL)) {
            return cb.equal(pathFilter, value);
        }

        return cb.like((Path<String>) pathFilter, "%" + value + "%");
    }
}
