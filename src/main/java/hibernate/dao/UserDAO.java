package hibernate.dao;

import hibernate.model.User;


import java.util.List;

public interface UserDAO{

    User getUserByName(String username);

    List<User> getAllUsers();
    User getUserById(long id);
    void addUser(User user);
    void updateUser(User user);
    void removeUser(User user);

}
