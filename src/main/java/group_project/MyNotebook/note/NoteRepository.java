package group_project.MyNotebook.note;

import group_project.MyNotebook.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    @Query("FROM Note n WHERE n.name LIKE :username")
    List<Note> findByUsername(String username);
    List<Note> findByUser(User user);
}
