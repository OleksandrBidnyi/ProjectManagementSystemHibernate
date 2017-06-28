package homework.mySqlDaoImpl;

import homework.hibernateUtil.HibernateUtil;
import homework.model.dao.CustomerDAO;
import homework.model.entities.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO<Customer> {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void create(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(customer);
            System.out.println(customer + "has been created");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer read(int id) {

        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Customer customer = (Customer) session.get(Customer.class, id);
                session.getTransaction().commit();
                return customer;
            } catch (HibernateException e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
        return null;
    }


    @Override
    public Collection<Customer> read() {
        try (Session session = sessionFactory.openSession()) {
            List<Customer> customerList = session.createQuery("From Customer").list();
            return customerList;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int id, Customer customer) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Customer updCustomer = (Customer) session.get(Customer.class, id);
            updCustomer.setName(customer.getName());
            session.update(updCustomer);
            session.getTransaction().commit();
            System.out.println(updCustomer + "has been updated");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Customer customer = (Customer) session.get(Customer.class, id);
            session.delete(customer);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}
