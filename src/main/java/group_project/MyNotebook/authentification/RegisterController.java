package group_project.MyNotebook.authentification;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @GetMapping
    public ModelAndView login() {
        return new ModelAndView("register");
    }

    @PostMapping("/create")
    public ModelAndView create() {
        //TODO implement code in comment below
        //@ModelAttribute("user") UserDto user
        //userService.save();
        return new ModelAndView("register");
    }
}
