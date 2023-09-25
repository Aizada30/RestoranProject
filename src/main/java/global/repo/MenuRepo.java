package global.repo;

import global.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Abdyrazakova Aizada
 */
@Repository
public interface MenuRepo extends JpaRepository<Menu, Long> {


}
