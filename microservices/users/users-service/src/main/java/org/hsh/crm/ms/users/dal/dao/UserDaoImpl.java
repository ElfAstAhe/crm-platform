package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.crm.ms.audit.bll.repository.UsersSettingsRepository;
import org.hsh.crm.ms.audit.dal.DalConstants;
import org.hsh.crm.ms.audit.dal.entities.User;
import microservice.common.dal.dao.BaseAuditableCrudDao;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.ExecutorService;

@Stateless
public class UserDaoImpl extends BaseAuditableCrudDao<User, String> implements UserDao {
    @PersistenceContext(unitName = DalConstants.PERSISTENCE_UNIT)

    @Resource
    private ManagedExecutorService executorService;

    @EJB
    private UsersSettingsRepository repoSettings;


    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return null;
    }

    @Override
    protected String getAuditUri() {
        return null;
    }

    @Override
    protected ExecutorService getExecutorService() {
        return null;
    }
}
