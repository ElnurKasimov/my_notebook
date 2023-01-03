package group_project.MyNotebook.note;

import group_project.MyNotebook.user.UserConverter;
import org.springframework.stereotype.Service;

@Service
public class NoteConverter {
    public NoteDto mapToDto(Note note) {
        NoteDto dto = new NoteDto();
        UserConverter userConverter = new UserConverter();
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
        UserConverter userConverter = new UserConverter();
        note.setId(dto.getId());
        note.setName(dto.getName());
        note.setContent(dto.getContent());
        note.setAccess(dto.getAccess());
        note.setHtml(dto.getHtml());
        note.setUser(userConverter.mapToDao(dto.getUser()));
        return note;
    }
}
