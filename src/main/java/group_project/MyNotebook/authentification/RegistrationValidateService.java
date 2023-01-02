package group_project.MyNotebook.authentification;

import group_project.MyNotebook.user.UserDto;
import group_project.MyNotebook.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationValidateService {
    private final UserService userService;

    public RegistrationStatus validate(UserDto user) {
        try {
            if (userService.isExistEmail(user.getEmail())) {
                return RegistrationStatus.invalidEmail;
            }
        } catch (EntityNotFoundException e) {
            return RegistrationStatus.userExists;
        }
        return RegistrationStatus.ok;
    }

    public enum RegistrationStatus {
        ok("Регистрация прошла успешно"),
        userExists("Пользователь с таким логином уже существует"),
        invalidEmail("Такой email уже зарегистрирован");

        private final String description;

        RegistrationStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
