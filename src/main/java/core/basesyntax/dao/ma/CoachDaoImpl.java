package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            Query<Coach> coachQuery = session.createQuery("from Coach "
                    + "where experience > :years", Coach.class);
            coachQuery.setParameter("years", years);
            return coachQuery.getResultList();
        } catch (HibernateException ex) {
            throw new RuntimeException("Find by experience greater than: " + years, ex);
        }
    }
}
