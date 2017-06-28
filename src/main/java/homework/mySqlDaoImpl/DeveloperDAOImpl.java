package homework.mySqlDaoImpl;

import homework.hibernateUtil.HibernateUtil;
import homework.model.dao.DeveloperDAO;
import homework.model.entities.Developer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

public class DeveloperDAOImpl implements DeveloperDAO<Developer> {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void create(Developer developer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(developer);
            System.out.println(developer + "has been created");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Developer read(int id) {

        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Developer developer = (Developer) session.get(Developer.class, id);
                session.getTransaction().commit();
                return developer;
            } catch (HibernateException e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public Collection<Developer> read() {
        try (Session session = sessionFactory.openSession()) {
            List<Developer> developerList = session.createQuery("From Developer").list();
            return developerList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int id, Developer developer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Developer updDeveloper = (Developer) session.get(Developer.class, id);
            updDeveloper.setName(developer.getName());
            updDeveloper.setCompanyId(developer.getCompanyId());
            updDeveloper.setSalary(developer.getSalary());
            session.update(updDeveloper);
            session.getTransaction().commit();
            System.out.println(updDeveloper + "has been updated");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Developer developer = (Developer) session.get(Developer.class, id);
            session.delete(developer);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
