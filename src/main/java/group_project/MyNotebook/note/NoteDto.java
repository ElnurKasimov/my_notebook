package group_project.MyNotebook.note;


import group_project.MyNotebook.user.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class NoteDto {

    private UUID id;
    private String content;
    private String name;
    private String html;
    private Access access;
    private UserDto user;
}
