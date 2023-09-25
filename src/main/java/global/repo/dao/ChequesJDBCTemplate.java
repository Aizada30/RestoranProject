package global.repo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import global.dto.response.ChequeResponse;
import global.dto.response.WholeMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Abdyrazakova Aizada
 */
@Repository
@RequiredArgsConstructor
public class ChequesJDBCTemplate {

    private final JdbcTemplate jdbcTemplate;

    public List<ChequeResponse> getAllCheques() {
        String sql = """
                        SELECT
                            ch.id AS id,
                            concat(u.first_name,' ',u.last_name) AS fullName,
                            array_agg(m.id) AS items,
                            sum(m.price) AS priceAvg,
                            sum(m.price+(m.price/100*r.service)) AS total,
                            ch.created_at AS date
                        FROM users AS u
                                 JOIN restaurants AS r ON u.restaurant_id = r.id
                                 LEFT JOIN cheques AS ch ON u.id = ch.user_id
                                 LEFT join cheques_menu_list cml ON ch.id = cml.cheque_list_id
                                 LEFT JOIN menues AS m ON cml.menu_list_id = m.id group by ch.id,u.first_name,u.last_name,r.service,m.id;
                """;
        return jdbcTemplate.query(sql, (resultSet, rowNumber) ->
                new ChequeResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("fullName"),
                        Collections.singletonList(resultSet.getLong("items")),
                        resultSet.getBigDecimal("priceAvg"),
                        resultSet.getBigDecimal("total"),
                        LocalDate.parse(resultSet.getString("date"))
                ));

    }
}
