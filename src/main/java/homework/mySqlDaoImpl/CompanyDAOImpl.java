package homework.mySqlDaoImpl;


import homework.hibernateUtil.HibernateUtil;
import homework.model.dao.CompanyDAO;
import homework.model.entities.Company;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO<Company> {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    @Override
    public void create(Company company) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(company);
            System.out.println(company + "has been created");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Company read(int id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Company company = (Company) session.get(Company.class, id);
                session.getTransaction().commit();
                return company;
            } catch (HibernateException e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public Collection<Company> read() {
        try (Session session = sessionFactory.openSession()) {
            List<Company> companyList = session.createQuery("From Company").list();
            return companyList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void update(int id, Company company) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Company updCompany = (Company) session.get(Company.class, id);
            updCompany.setName(company.getName());
            session.update(updCompany);
            session.getTransaction().commit();
            System.out.println(updCompany + "has been updated");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Company company = (Company) session.get(Company.class, id);
            session.delete(company);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}
