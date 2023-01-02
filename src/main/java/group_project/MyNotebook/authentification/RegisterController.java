package group_project.MyNotebook.authentification;

import group_project.MyNotebook.role.RoleService;
import group_project.MyNotebook.user.UserDto;
import group_project.MyNotebook.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

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
    public ModelAndView create(@ModelAttribute("user") UserDto user) {
        ModelAndView register = new ModelAndView("register");
        RegistrationValidateService.RegistrationStatus validateStatus = validateService.validate(user);
        if (validateStatus.equals(RegistrationValidateService.RegistrationStatus.ok)) {
            createUser(user);
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
