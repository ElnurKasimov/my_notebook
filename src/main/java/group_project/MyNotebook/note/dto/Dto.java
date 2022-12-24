package group_project.MyNotebook.note.dto;

import group_project.MyNotebook.note.Access;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Dto {

    private UUID id;

    @Column(name = "content")
    private String content;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Access access;
}
