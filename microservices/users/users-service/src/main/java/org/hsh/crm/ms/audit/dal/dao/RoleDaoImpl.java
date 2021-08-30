package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.crm.ms.audit.bll.repository.UsersSettingsRepository;
import org.hsh.crm.ms.audit.bll.settings.UsersSettingsEnum;
import org.hsh.crm.ms.audit.dal.DalConstants;
import org.hsh.crm.ms.audit.dal.entities.Role;
import microservice.common.dal.dao.BaseAuditableCrudDao;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.concurrent.ExecutorService;

@Stateless
public class RoleDaoImpl extends BaseAuditableCrudDao<Role, String> {
    @PersistenceContext(unitName = DalConstants.PERSISTENCE_UNIT)
    private EntityManager em;

    @EJB
    private UsersSettingsRepository repoSettings;

    @Resource
    private ManagedExecutorService executorService;

    public RoleDaoImpl() {
        super(Role.class);
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
