package org.hsh.crm.ms.infra.dal.dao;

import org.hsh.crm.ms.infra.dal.dao.base.BaseInfraDao;
import org.hsh.crm.ms.infra.dal.entities.Resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class ResourceDao extends BaseInfraDao<Resource, String> {
    public ResourceDao() {
        super(Resource.class);
    }
}
