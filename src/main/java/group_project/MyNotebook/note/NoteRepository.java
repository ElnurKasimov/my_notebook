package group_project.MyNotebook.note;

import group_project.MyNotebook.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {

    @Query("FROM Note n WHERE n.user.id LIKE :userId")
    List<Note> findByUserId(UUID userId);
    List<Note> findByUser(User user);
}
