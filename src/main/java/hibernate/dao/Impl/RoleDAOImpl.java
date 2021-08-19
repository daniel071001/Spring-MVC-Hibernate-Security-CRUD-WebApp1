package hibernate.dao.Impl;

import hibernate.dao.RoleDAO;
import hibernate.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    public final SessionFactory sessionFactory;

    public RoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Role").list();
    }

    @Override
    @Transactional
    public List<String> getRoleNamesToList(){
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("select role from Role").getResultList();
    }

    @Override
    @Transactional
    public Role getRoleByName(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select role from Role role where role.role=:role");
        query.setParameter("role", username);
        return (Role) query.getSingleResult();
    }

}

