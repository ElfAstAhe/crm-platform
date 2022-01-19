package org.hsh.crm.ms.infra.dal.dao;

import org.hsh.crm.ms.infra.dal.dao.base.BaseInfraDao;
import org.hsh.crm.ms.infra.dal.entities.Network;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class NetworkDao extends BaseInfraDao<Network, String> {
    public NetworkDao() {
        super(Network.class);
    }
}
