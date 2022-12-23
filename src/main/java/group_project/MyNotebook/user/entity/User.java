package group_project.MyNotebook.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO, generator = "uuid")
    @Column(name = "id")
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String name;

    @Column(name = "password")
    private String password;

}
