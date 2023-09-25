package global.repo;

import global.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Abdyrazakova Aizada
 */
@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {

}
