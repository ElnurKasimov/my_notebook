package group_project.MyNotebook.authentification;

import group_project.MyNotebook.role.RoleService;
import group_project.MyNotebook.user.UserDto;
import group_project.MyNotebook.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("user") UserDto user) {
        ModelAndView register = new ModelAndView("register");
        try {
            if (userService.isExistEmail(user.getEmail())) {
                throw new RuntimeException(
                        String.format("%s Email уже зарегистрирован", user.getEmail()));
            }
            createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return register.addObject("errorMessage", e.getMessage());
        }
        return new ModelAndView("login");
    }

    private void createUser(UserDto user){
        userService.setPassword(user, user.getPassword());
        user.setRoles(List.of(roleService.findByName("ROLE_USER")));
        userService.create(user);
    }
}
