package hibernate.service;

import hibernate.model.User;

import java.util.List;

public interface UserService {

    User getUserByName(String name);

    List<User> getAllUsers();
    User getUserById(long id);
    void addUser(User user);
    void updateUser(User user);
    void removeUser(User user);
}
