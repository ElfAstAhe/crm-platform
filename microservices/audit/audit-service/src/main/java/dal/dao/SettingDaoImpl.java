package dal.dao;

import common.dal.dao.BaseCrudDao;
import dal.DalConstants;
import microservice.common.dal.entities.Setting;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings({"JpaQueryApiInspection"})
@Stateless
public class SettingDaoImpl extends BaseCrudDao<Setting, String> implements SettingDao {
    private static final Logger logger = Logger.getLogger(SettingDaoImpl.class.getName());
    @PersistenceContext(unitName = DalConstants.PERSISTENCE_UNIT)
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
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "error fetch data", ex);
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
