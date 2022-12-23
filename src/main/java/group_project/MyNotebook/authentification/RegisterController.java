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
        ModelAndView register = new ModelAndView("register");
        try {
            //TODO implement code in comment below
            //@ModelAttribute("user") UserDto user
            //userService.save();
            throw new RuntimeException("Saving option is under development");
        }catch (Exception e) {
            e.printStackTrace();
            return register.addObject("errorMessage", e.getMessage());
        }
    }
}
