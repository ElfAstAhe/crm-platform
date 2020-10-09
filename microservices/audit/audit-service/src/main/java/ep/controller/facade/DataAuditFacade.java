package ep.controller.facade;

import common.ep.facade.CrudFacade;
import dto.audit.DataAudit;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class DataAuditFacade implements CrudFacade<DataAudit> {
    @Override
    public DataAudit getInstance(Object id) {
        return null;
    }

    @Override
    public List<DataAudit> listAllInstances() {
        return null;
    }

    @Override
    public DataAudit createInstance(DataAudit instance) {
        return null;
    }

    @Override
    public DataAudit editInstance(Object id, DataAudit instance) {
        return null;
    }

    @Override
    public void removeInstance(Object id) {

    }
}
