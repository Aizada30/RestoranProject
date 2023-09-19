package global.repo;

import global.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Abdyrazakova Aizada
 */
@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

}
