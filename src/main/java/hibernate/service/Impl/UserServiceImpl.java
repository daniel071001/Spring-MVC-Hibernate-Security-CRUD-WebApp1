package hibernate.service.Impl;

import hibernate.dao.UserDAO;
import hibernate.model.User;
import hibernate.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public User getUserByName(String name) {
        return this.userDAO.getUserByName(name);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(User user) {
        this.userDAO.removeUser(user);
    }
}

