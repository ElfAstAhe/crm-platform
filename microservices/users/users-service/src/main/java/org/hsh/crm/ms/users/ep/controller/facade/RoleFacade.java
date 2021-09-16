package org.hsh.crm.ms.users.ep.controller.facade;

import org.hsh.crm.ms.users.dal.dao.RoleDao;
import org.hsh.crm.ms.users.dto.RoleDto;
import org.hsh.crm.ms.users.dto.convertor.RoleConvertor;
import org.hsh.ms.common.ep.facade.CrudFacade;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class RoleFacade implements CrudFacade<RoleDto> {
    @EJB
    private RoleDao daoRole;

    @Override
    public RoleDto get(Object id) {
        return RoleConvertor.toDto(daoRole.find(id));
    }

    @Override
    public List<RoleDto> listAll() {
        return daoRole.listAll()
                      .stream()
                      .map(RoleConvertor::toDto)
                      .collect(Collectors.toList());
    }

    @Override
    public RoleDto create(RoleDto role) {
        return RoleConvertor.toDto(daoRole.create(RoleConvertor.toEntity(role, null)));
    }

    @Override
    public RoleDto edit(Object id, RoleDto role) {
        return RoleConvertor.toDto(daoRole.edit(RoleConvertor.toEntity(role, daoRole::find)));
    }

    @Override
    public void remove(Object id) {
        daoRole.remove(id);
    }
}
