package group_project.MyNotebook.note;

import group_project.MyNotebook.user.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoteConverter {
    private final UserConverter userConverter;
    public NoteDto mapToDto(Note note) {
        NoteDto dto = new NoteDto();
        dto.setId(note.getId());
        dto.setName(note.getName());
        dto.setContent(note.getContent());
        dto.setHtml(note.getHtml());
        dto.setAccess(note.getAccess());
        dto.setUser(userConverter.mapToDto(note.getUser()));
        return dto;
    }

    public Note mapToDao(NoteDto dto){
        Note note = new Note();
        note.setId(dto.getId());
        note.setName(dto.getName());
        note.setContent(dto.getContent());
        note.setAccess(dto.getAccess());
        note.setHtml(dto.getHtml());
        note.setUser(userConverter.mapToDao(dto.getUser()));
        return note;
    }
}
