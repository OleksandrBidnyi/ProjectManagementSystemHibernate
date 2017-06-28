package homework.mySqlDaoImpl;

import homework.hibernateUtil.HibernateUtil;
import homework.model.dao.ProjectDAO;
import homework.model.entities.Project;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

public class ProjectDAOImpl implements ProjectDAO<Project> {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void create(Project project) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(project);
            System.out.println(project + "has been created");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project read(int id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Project project = (Project) session.get(Project.class, id);
                session.getTransaction().commit();
                return project;
            } catch (HibernateException e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public Collection<Project> read() {
        try (Session session = sessionFactory.openSession()) {
            List<Project> projectList = session.createQuery("From Project").list();
            return projectList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int id, Project project) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Project updProject = (Project) session.get(Project.class, id);
            updProject.setName(project.getName());
            updProject.setCompanyId(project.getCompanyId());
            updProject.setCustomerId(project.getCustomerId());
            updProject.setCost(project.getCost());
            session.update(updProject);
            session.getTransaction().commit();
            System.out.println(updProject + "has been updated");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Project project = (Project) session.get(Project.class, id);
            session.delete(project);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}
