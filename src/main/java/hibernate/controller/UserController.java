package hibernate.controller;

import hibernate.dao.RoleDAO;
import hibernate.model.Role;
import hibernate.model.User;
import hibernate.service.RoleService;
import hibernate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
//    private final RoleService roleService;
    private final RoleDAO roleDAO;

    public UserController(UserService userService, RoleDAO roleDao) {
        this.userService = userService;
        this.roleDAO= roleDao;
    }

    @GetMapping("/")
    public String getHomePage(){
        return "home-page";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/user")
    public String getUser(Principal principal, Model model){
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        return "user-page";
    }

    @GetMapping("/admin")
    public String listUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-add")
    public String createUserForm(@ModelAttribute("user") User user, Model model){
        model.addAttribute("user", user);
        List<Role> roles = roleDAO.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "user-add";
    }

    @PostMapping("/user-add")       //@RequestParam-это параметр, привязанный как параметр запроса. Этот параметр может быть необязательным, например, для применения фильтра.
    public String createUser(User user, @RequestParam Map<String, String> form){
        List<String> roles = roleDAO.getRoleNamesToList();
        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key: form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleDAO.getRoleByName(key));
            }
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")  //PathVariable - это параметр, привязанный как параметр в url. Этот параметр должен быть информирован.
    public String updateUserForm(@PathVariable("id") long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        List<Role> roles = roleDAO.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user")User user, @RequestParam Map<String, String> form){
        List<String> roles = roleDAO.getRoleNamesToList();
        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key: form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleDAO.getRoleByName(key));
            }
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.removeUser(userService.getUserById(id));
        return "redirect:/admin";
    }
}

