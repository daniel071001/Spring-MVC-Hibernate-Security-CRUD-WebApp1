package hibernate.dao.Impl;

import hibernate.dao.UserDAO;
import hibernate.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public User getUserByName(String username) {
        List<User> users = getAllUsers();
        return users.stream().filter(x->x.getUsername().equals(username)).findAny().orElse(new User());
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    @Transactional
    public void removeUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.remove(session.contains(user) ? user : session.merge(user));
    }
}

