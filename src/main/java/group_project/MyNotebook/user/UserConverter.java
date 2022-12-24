package group_project.MyNotebook.user;

import org.springframework.stereotype.Service;

@Service
public class UserConverter {
    public Dto mapToDto(User user) {
        Dto dto = new Dto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRoles(user.getRoles());
        return dto;
    }

    public User mapToDao(Dto dto){
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setRoles(dto.getRoles());
        return user;
    }
}
