package group_project.MyNotebook.authentification;

import group_project.MyNotebook.user.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegistrationValidateService {
    private Pattern pattern;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        pattern = Pattern
                .compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Pattern.CASE_INSENSITIVE);

    }

    public RegistrationStatus validate(String email, String password, String userName) {
        if (!isEmailValid(email)) {
            return RegistrationStatus.invalidEmail;
        }

        if (userService.findByUsername() != null) {
            return RegistrationStatus.userExists;
        }

        if (!isPasswordValid(password)) {
            return RegistrationStatus.invalidPassword;
        }

        if (!isNameValid(userName)) {
            return RegistrationStatus.invalidFirstName;
        }

        return RegistrationStatus.ok;

    }

    private boolean isEmailValid(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }

    private boolean isNameValid(String name) {
        return name != null && name.trim().length() > 0;
    }

    public enum RegistrationStatus {
        ok("Ви успішно зареєструвалися"),
        userExists("Користувач з таким email вже існує"),
        invalidEmail("Неправильна поштова адреса"),
        invalidPassword("Довжина пароля повинна складати 8 або більше символів"),
        invalidFirstName("Введіть ваше ім'я"),
        invalidLastName("Введіть ваше прізвище");

        RegistrationStatus(String description) {
            this.description = description;
        }

        private String description;

        public String getDescription() {
            return description;
        }
    }

    public static void main(String[] args) {
        String email = " test2@gmail.com";

        RegistrationValidateService validateService = new RegistrationValidateService();
        validateService.init();
        boolean result = validateService.isEmailValid(email);
        System.out.println(result);
    }
}
