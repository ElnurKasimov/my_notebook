package group_project.MyNotebook.authentification;

import group_project.MyNotebook.user.UserDto;
import group_project.MyNotebook.user.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class RegistrationValidateService {
    private Pattern pattern;
    private final UserService userService;

    @PostConstruct
    public void init() {
        pattern = Pattern
                .compile("^[a-zA-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    }

    public RegistrationStatus validate(UserDto user) {
        try {
            if (userService.isExistEmail(user.getEmail())) {
                return RegistrationStatus.invalidEmail;
            }
            if (!isLatin(user.getName())) {
                return RegistrationStatus.userNameEx;
            }
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        return RegistrationStatus.ok;
    }

    public enum RegistrationStatus {
        ok("Регистрация прошла успешно"),
        userNameEx("Введите имя латинскими символами и цифрами"),
        invalidEmail("Такой email уже зарегистрирован");

        private final String description;

        RegistrationStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    private boolean isLatin(String userName) {
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }
}
