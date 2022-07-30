package music.replay.repositories;

import music.replay.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query(value = "SELECT username FROM users WHERE role = 'ADMIN'", nativeQuery = true)
    List<String> getAdmins();
}
