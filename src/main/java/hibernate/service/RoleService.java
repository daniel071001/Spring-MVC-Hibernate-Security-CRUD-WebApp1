package hibernate.service;

import hibernate.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();
    List<String> getRoleNamesToList();
    Role getRoleByName(String username);
}
