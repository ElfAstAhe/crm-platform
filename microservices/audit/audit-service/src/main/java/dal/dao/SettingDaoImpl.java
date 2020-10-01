package dal.dao;

import common.dal.dao.BaseCrudDao;
import dal.entities.Setting;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SuppressWarnings({"JpaQueryApiInspection"})
@Stateless
public class SettingDaoImpl extends BaseCrudDao<Setting, String> implements SettingDao{
    @PersistenceContext(unitName = "audit.PU")
    private EntityManager em;

    public SettingDaoImpl() {
        super(Setting.class);
    }

    @Override
    public Setting findByKey(String key) {
        try {
            return em.createNamedQuery("Setting.findByCode", getEntityClass())
                    .setParameter("code", key)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
