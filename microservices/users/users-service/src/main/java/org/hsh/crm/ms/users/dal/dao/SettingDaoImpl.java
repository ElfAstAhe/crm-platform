package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.crm.ms.audit.dal.DalConstants;
import org.hsh.crm.ms.common.dal.dao.BaseAuditableCrudDao;
import org.hsh.crm.ms.common.dal.entities.Setting;
import org.hsh.crm.ms.users.bll.repository.UsersSettingsRepository;
import org.hsh.crm.ms.users.bll.settings.UsersSettingsEnum;
import org.hsh.crm.ms.users.dal.dao.SettingDao;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.ExecutorService;

@Stateless
public class SettingDaoImpl extends BaseAuditableCrudDao<Setting, String> implements SettingDao {
    @PersistenceContext(unitName = DalConstants.PERSISTENCE_UNIT)
    private EntityManager em;

    @EJB
    private UsersSettingsRepository repoSettings;

    @Resource
    private ManagedExecutorService executorService;

    public SettingDaoImpl() {
        super(Setting.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected String getAuditUri() {
        return repoSettings.getStringValue(UsersSettingsEnum.AUDIT_SERVICE);
    }

    @Override
    protected ExecutorService getExecutorService() {
        return executorService;
    }
}
