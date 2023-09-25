package global.repo;

import global.entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Abdyrazakova Aizada
 */
@Repository
public interface ChequeRepo extends JpaRepository<Cheque,Long> {

    @Query("select sum (m.price)from User u join u.chequeList c join c.menuList m where u.id=:id and " +
            "c.createdAt = :date")
    BigDecimal getTopByCreatedAt(LocalDate date, Long id);

    @Query("select avg(m.price) from Restaurant r join r.userList u join u.chequeList c join c.menuList m where r.id=1 and c.createdAt=:date")
    BigDecimal avg(LocalDate date);

}
