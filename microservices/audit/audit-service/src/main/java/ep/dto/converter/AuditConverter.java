package ep.dto.converter;

import dto.audit.Audit;
import dto.audit.builders.AuditBuilder;

public class AuditConverter {
    private AuditConverter() {
        // hide
    }

    public static Audit toDto(bll.model.Audit model) {
        return AuditBuilder.get()
                .setId(model.getId())
                .setEventDate(model.getEventDate())
                .setEvent(model.getEvent())
                .setSource(model.getSource())
                .setRequestId(model.getRequestId())
                .setUser(model.getUser())
                .setStatus(model.getStatus())
                .setAdditional(model.getAdditional())
                .build();
    }
}
