package hibernate.service.Impl;

import hibernate.dao.RoleDAO;
import hibernate.model.Role;
import hibernate.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    public final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    @Transactional
    public List<String> getRoleNamesToList() {
        return roleDAO.getRoleNamesToList();
    }

    @Override
    @Transactional
    public Role getRoleByName(String username) {
        return roleDAO.getRoleByName(username);
    }

}

