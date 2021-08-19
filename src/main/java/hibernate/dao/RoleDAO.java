package hibernate.dao;


import hibernate.model.Role;

import java.util.List;

public interface RoleDAO{

    List<Role> getAllRoles();
    List<String> getRoleNamesToList();
    Role getRoleByName(String username);
}