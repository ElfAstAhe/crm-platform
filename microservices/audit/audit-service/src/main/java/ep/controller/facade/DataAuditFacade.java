package ep.controller.facade;

import common.ep.facade.CrudFacade;
import dal.dao.DataAuditDao;
import dto.audit.DataAudit;
import ep.dto.converter.DataAuditConverter;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class DataAuditFacade implements CrudFacade<DataAudit> {
    @EJB
    private DataAuditDao daoDataAudit;

    @Override
    public DataAudit getInstance(Object id) {
        return DataAuditConverter.toDto(daoDataAudit.find(id));
    }

    @Override
    public List<DataAudit> listAllInstances() {
        return daoDataAudit.listAll()
                .stream()
                .map(DataAuditConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DataAudit createInstance(DataAudit instance) {
        return DataAuditConverter.toDto(daoDataAudit.create(DataAuditConverter.toEntity(instance, null)));
    }

    @Override
    public DataAudit editInstance(Object id, DataAudit instance) {
        return DataAuditConverter.toDto(daoDataAudit.edit(DataAuditConverter.toEntity(instance, daoDataAudit::find)));
    }

    @Override
    public void removeInstance(Object id) {
        daoDataAudit.remove(id);
    }
}
