package group_project.MyNotebook.note;

import org.springframework.stereotype.Service;

@Service
public class NoteConverter {
    public Dto mapToDto(Note note) {
        Dto dto = new Dto();
        dto.setId(note.getId());
        dto.setName(note.getName());
        dto.setContent(note.getContent());
        dto.setAccess(note.getAccess());
        dto.setUser(note.getUser());
        return dto;
    }

    public Note mapToDao(Dto dto){
        Note note = new Note();
        note.setId(dto.getId());
        note.setName(dto.getName());
        note.setContent(dto.getContent());
        note.setAccess(dto.getAccess());
        note.setUser(dto.getUser());
        return note;
    }
}
