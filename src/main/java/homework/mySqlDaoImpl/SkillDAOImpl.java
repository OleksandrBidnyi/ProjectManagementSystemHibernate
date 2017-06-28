package homework.mySqlDaoImpl;

import homework.hibernateUtil.HibernateUtil;
import homework.model.entities.Skill;
import homework.model.dao.SkillDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

public class SkillDAOImpl implements SkillDAO<Skill> {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void create(Skill skill) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(skill);
            System.out.println(skill + "has been created");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Skill read(int id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Skill skill = (Skill) session.get(Skill.class, id);
                session.getTransaction().commit();
                return skill;
            } catch (HibernateException e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public Collection<Skill> read() {
        try (Session session = sessionFactory.openSession()) {
            List<Skill> skillList = session.createQuery("From Skill").list();
            return skillList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int id, Skill skill) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Skill updSkill = (Skill) session.get(Skill.class, id);
            updSkill.setSkill(skill.getSkill());
            session.update(updSkill);
            session.getTransaction().commit();
            System.out.println(updSkill + "has been updated");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Skill skill = (Skill) session.get(Skill.class, id);
            session.delete(skill);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
