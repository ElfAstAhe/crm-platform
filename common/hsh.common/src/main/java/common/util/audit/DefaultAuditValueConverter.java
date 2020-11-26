package common.util.audit;

public class DefaultAuditValueConverter implements AuditValueConverter{
    @Override
    public String toAuditValue(Object value) {
        if (value == null)
            return null;
        return value.getClass().getName();
    }
}
