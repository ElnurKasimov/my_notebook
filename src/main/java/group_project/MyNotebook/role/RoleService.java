package group_project.MyNotebook.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@SuppressWarnings({"unused", "ClassCanBeRecord"})
@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;

    public List<Role> findAll(){
        return repository.findAll();
    }
    public Role findById(UUID id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public boolean idFound(UUID id){
        return repository.existsById(id);
    }
    public Role findByName(String name){
        return repository.findByName(name);
    }

}
