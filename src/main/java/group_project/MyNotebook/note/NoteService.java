package group_project.MyNotebook.note;

import group_project.MyNotebook.user.UserConverter;
import group_project.MyNotebook.user.UserDto;
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
public class NoteService {
    private final NoteConverter noteConverter;
    private final NoteRepository noteRepository;

    public List<NoteDto> findAll() {
        return noteRepository.findAll(Sort.by("name"))
                .stream()
                .map(noteConverter::mapToDto)
                .collect(Collectors.toList());
    }

    public List<NoteDto> findAll(String username) {
        return noteRepository.findByUsername(username)
                .stream()
                .map(noteConverter::mapToDto)
                .collect(Collectors.toList());
    }

    public List<NoteDto> findAll(UserDto user){
        return noteRepository.findByUser(new UserConverter().mapToDao(user))
                .stream()
                .map(noteConverter::mapToDto)
                .collect(Collectors.toList());
    }

        public NoteDto get(UUID id){
        return noteRepository.findById(id)
                .map(noteConverter::mapToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

        public UUID create(NoteDto noteDto) {
        return noteRepository.save(noteConverter.mapToDao(noteDto)).getId();
            }
        public void update(UUID id, NoteDto noteDto) {
        noteDto.setId(id);
        noteRepository.findById(id)
                .map((n) -> noteRepository.save(noteConverter.mapToDao(noteDto)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

    public void delete(UUID id) {
        noteRepository.deleteById(id);
    }
}
