package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.crm.ms.common.dal.entities.Setting;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class SettingDaoImpl extends BaseAuditDao<Setting, String> implements SettingDao {
    public SettingDaoImpl() {
        super(Setting.class);
    }

    @Override
    public Setting findByKey(String key) {
        try {
            return getEntityManager().createNamedQuery("Setting.findByCode", getEntityClass())
                                     .setParameter("code", key)
                                     .setMaxResults(1)
                                     .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
