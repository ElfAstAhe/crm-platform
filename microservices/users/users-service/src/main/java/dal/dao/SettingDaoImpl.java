package dal.dao;

import bll.repository.UsersSettingsRepository;
import bll.settings.UsersSettingsEnum;
import dal.DalConstants;
import microservice.common.dal.dao.BaseAuditableCrudDao;
import microservice.common.dal.entities.Setting;

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
