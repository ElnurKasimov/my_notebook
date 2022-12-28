package group_project.MyNotebook.authentification;

import group_project.MyNotebook.user.UserDto;
import group_project.MyNotebook.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class SecurityController {
    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationValidateService registrationValidateService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView result = new ModelAndView("security/login");

        result.addObject("user", userService.findByUsername());

        return result;
    }

    @GetMapping("/register")
    public ModelAndView register(@RequestParam(required = false)  RegistrationValidateService.RegistrationStatus status) {
        ModelAndView result = new ModelAndView("security/register");
        result.addObject("user", userService.findByUsername());

        if (status != null) {
            result.addObject("status", status);
        }

        return result;
    }

    @PostMapping("/register")
    public String handleRegister(String email, String password, String userName) {

        email = email.trim();
        userName = userName.trim();

        RegistrationValidateService.RegistrationStatus status = registrationValidateService.validate(email, password, userName);

        if (status == RegistrationValidateService.RegistrationStatus.ok) {
            UserDto newUser = new UserDto();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setName(userName);
            userService.create(newUser);
        }

        return "redirect:/register?status=" + status;
    }

    @GetMapping("/profile")
    public ModelAndView getProfile() {
        ModelAndView result = new ModelAndView("security/profile");

        result.addObject("user", userService.findByUsername());

        return result;
    }

    @PostMapping("/profile")
    public String postProfile(@RequestParam(name = "name") String name,
                              @RequestParam(name = "password", required = false) String password) {
        UserDto user = userService.findByUsername();

        if (!name.isEmpty()) {
            user.setName(name);
        }

        if (password != null && !password.isEmpty()) {
            userService.setPassword(user, password);
        }

        userService.update(user.getId(), user);

        return "redirect:/profile";
    }
}
