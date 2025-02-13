package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users (" +
                                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                                        "name VARCHAR(255), " +
                                        "lastName VARCHAR(255), " +
                                        "age SMALLINT)").executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try(Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("User " + name + " saved");
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if(user != null) {
                session.delete(user);
                System.out.println("User " + id + " removed");
            }
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = getSessionFactory().openSession()){
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("DELETE  FROM users").executeUpdate();
            transaction.commit();
        }
    }
}
