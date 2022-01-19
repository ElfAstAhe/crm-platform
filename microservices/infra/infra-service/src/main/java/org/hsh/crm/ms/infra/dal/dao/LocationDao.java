package org.hsh.crm.ms.infra.dal.dao;

import org.hsh.crm.ms.infra.dal.dao.base.BaseInfraDao;
import org.hsh.crm.ms.infra.dal.entities.Location;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class LocationDao extends BaseInfraDao<Location, Long> {
    public LocationDao() {
        super(Location.class);
    }
}
