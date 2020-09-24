package common.dal.dao;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

public enum FilterClassTypeEnum {
    BOOLEAN(Boolean.class),
    INTEGER(Integer.class),
    LONG(Long.class),
    DOUBLE(Double.class),
    BIG_DECIMAL(BigDecimal.class),
    STRING(String.class),
    ENUM(Enum.class),
    BOOLEAN_ARR(Boolean[].class),
    INTEGER_ARR(Integer[].class),
    LONG_ARR(Long[].class),
    DOUBLE_ARR(Double[].class),
    BIG_DECIMAL_ARR(BigDecimal[].class),
    STRING_ARR(String[].class),
    OBJECT_ARR(Object[].class),
    DATE(Date.class),
    OFFSET_DATE_TIME(OffsetDateTime.class),
    LIST(List.class),
    UNKNOWN(null);

    private final Class<?> clazz;

    FilterClassTypeEnum(Class<?> clazz) {
        this.clazz = clazz;
    }

    public static FilterClassTypeEnum classToEnum(Object instance) {
        for(FilterClassTypeEnum _enum : FilterClassTypeEnum.values()) {
            if (_enum.getClazz() != null && _enum.getClazz() == instance.getClass()) {
                return _enum;
            }
        }

        return UNKNOWN;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
