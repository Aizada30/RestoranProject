package global.repo;

import global.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User>findByEmail(String email);

    @Query("SELECT COUNT(r.id) FROM User u JOIN Restaurant r ON u.restaurant.id=r.id")
    int numberOfEmployees(Long restaurantId);
}