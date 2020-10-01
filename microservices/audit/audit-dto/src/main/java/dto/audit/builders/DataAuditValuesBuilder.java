package dto.audit.builders;

import common.util.audit.AuditUtils;
import dto.audit.DataAuditValue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataAuditValuesBuilder {
    private DataAuditValuesBuilder() {
        // hide constructor
    }

    public static DataAuditValuesBuilder get() {
        return new DataAuditValuesBuilder();
    }

    public List<DataAuditValue> buildCreated(Object afterInstance) {
        return build(null, afterInstance);
    }

    public List<DataAuditValue> buildModified(Object beforeInstance, Object afterInstance) {
        return build(beforeInstance, afterInstance);
    }

    public List<DataAuditValue> buildRemoved(Object beforeInstance) {
        return build(beforeInstance, null);
    }

    private List<DataAuditValue> build(Object beforeInstance, Object afterInstance) {
        switch (EventCondition.getCondition(beforeInstance, afterInstance)) {
            case CREATED:
                return AuditUtils.buildAllAnnotatedFieldList(afterInstance)
                        .stream()
                        .map(f -> DataAuditValueBuilder.get()
                                .setName(f.getName())
                                .setDescription(AuditUtils.fieldDescription(f))
                                .setAfter(AuditUtils.valueOrNull(afterInstance, f))
                                .build())
                        .collect(Collectors.toList());
            case MODIFIED:
                return AuditUtils.buildAllAnnotatedFieldList(afterInstance)
                        .stream()
                        .map(f -> DataAuditValueBuilder.get()
                                .setName(f.getName())
                                .setDescription(AuditUtils.fieldDescription(f))
                                .setBefore(AuditUtils.valueOrNull(beforeInstance, f))
                                .setAfter(AuditUtils.valueOrNull(afterInstance, f))
                                .build())
                        .collect(Collectors.toList());
            case REMOVED:
                return AuditUtils.buildAllAnnotatedFieldList(beforeInstance)
                        .stream()
                        .map(f -> DataAuditValueBuilder.get()
                                .setName(f.getName())
                                .setDescription(AuditUtils.fieldDescription(f))
                                .setBefore(AuditUtils.valueOrNull(beforeInstance, f))
                                .build())
                        .collect(Collectors.toList());
            default:
                return new ArrayList<>();
        }
    }

    private interface ConditionChecker {
        boolean check(Object beforeInstance, Object afterInstance);
    }

    private enum EventCondition {
        NONE((beforeInstance, afterInstance) -> beforeInstance == null && afterInstance == null),
        CREATED((beforeInstance, afterInstance) -> beforeInstance == null && afterInstance != null),
        MODIFIED((beforeInstance, afterInstance) -> beforeInstance != null && afterInstance != null),
        REMOVED((beforeInstance, afterInstance) -> beforeInstance != null && afterInstance == null);

        private final ConditionChecker checker;

        EventCondition(ConditionChecker checker) {
            this.checker = checker;
        }

        public static EventCondition getCondition(Object beforeInstance, Object afterInstance) {
            for(EventCondition eventCondition : values()) {
                if (eventCondition.checker.check(beforeInstance, afterInstance)) {
                    return eventCondition;
                }
            }

            return EventCondition.NONE;
        }
    }
}
