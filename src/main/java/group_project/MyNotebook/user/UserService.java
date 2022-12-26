package group_project.MyNotebook.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserConverter converter;

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
    public UUID create(UserDto dto){
        return repository.save(converter.mapToDao(dto)).getId();
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

    public UserDto findByUsername(String username){
        return repository.findByEmail(username)
                .map(converter::mapToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public boolean isExistEmail(String email){
        return repository.existsByEmail(email);
    }

}
