package group_project.MyNotebook.note;


import group_project.MyNotebook.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;

}
