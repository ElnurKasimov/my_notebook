package group_project.MyNotebook.authentification;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
