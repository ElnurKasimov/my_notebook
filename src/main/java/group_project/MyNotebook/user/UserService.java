package group_project.MyNotebook.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SuppressWarnings({"ClassCanBeRecord", "unused"})
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserConverter converter;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<UserDto> findAll(){
        return repository.findAll(Sort.by("email"))
                .stream()
                .map(converter::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto get(UUID id) {
        return repository.findById(id)
                .map(converter::mapToDto)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public void create(UserDto dto){
        repository.save(converter.mapToDao(dto));
    }

    public void update(UUID id, UserDto dto){
        dto.setId(id);
        repository.findById(id)
                .map((p)->repository.save(converter.mapToDao(dto)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

    public UserDto findByEmail(String email){
        return repository.findByEmail(email)
                .map(converter::mapToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void setPassword(UserDto user, String password) {
        String passwordHash = passwordEncoder.encode(password);
        user.setPassword(passwordHash);
    }

    public boolean isExistEmail(String email){
        return repository.existsByEmail(email);
    }

}
