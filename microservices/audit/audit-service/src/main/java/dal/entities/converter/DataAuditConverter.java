package dal.entities.converter;

import bll.model.Audit;
import dal.entities.DataAudit;

public final class DataAuditConverter {
    private DataAuditConverter() {
        // hide
    }

    public static Audit toAudit(DataAudit entity) {
        return null;
    }

    private static String toAuditAdditional(DataAudit entity) {
        return null;
    }
}
