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

@SuppressWarnings("ClassCanBeRecord")
@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final RoleService roleService;
    private final RegistrationValidateService validateService;

    @GetMapping
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("user") UserDto user, @ModelAttribute("confirmPassword") String confirm) {
        ModelAndView register = new ModelAndView("register");
        ModelAndView login = new ModelAndView("login");
        RegistrationValidateService.RegistrationStatus validateStatus = validateService.validate(user, confirm);
        if (validateStatus.equals(RegistrationValidateService.RegistrationStatus.ok)) {
            createUser(user);
            return login.addObject("status", validateStatus);
        }
        register.addObject("status", validateStatus);
        return register;
    }

    private void createUser(UserDto user) {
        userService.setPassword(user, user.getPassword());
        user.setRoles(List.of(roleService.findByName("ROLE_USER")));
        userService.create(user);
    }
}
