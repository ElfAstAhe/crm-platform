package ep.controller.facade;

import common.ep.facade.CrudFacade;
import dal.dao.DataAuditDao;
import dto.audit.DataAudit;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class DataAuditFacade implements CrudFacade<DataAudit> {
    @EJB
    private DataAuditDao daoDataAudit;

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
