package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.crm.ms.common.dal.entities.Setting;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class SettingDaoImpl extends BaseAuditDao<Setting, String> implements SettingDao{
    public SettingDaoImpl() {
        super(Setting.class);
    }

    @Override
    public Setting findByKey(String key) {
        try {
            return getEntityManager().createNamedQuery("Setting.findByCode", getEntityClass())
                                     .setParameter("code", key)
                                     .setLockMode(LockModeType.NONE)
                                     .setMaxResults(1)
                                     .getSingleResult();
        } catch(NoResultException ex) {
            return null;
        }
    }
}
