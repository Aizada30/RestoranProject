package global.repo.dao;

import global.dto.response.RestaurantResponse;
import global.entity.enums.RestType;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantJDBCTemplate {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<RestaurantResponse> restaurantRowMapper = (resultSet, rowNumber) -> {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String location = resultSet.getString("location");

        String restTypeString = resultSet.getString("rest_type");
        RestType restType = (restTypeString != null) ? RestType.valueOf(restTypeString) : null;

        int numberOfEmployees = resultSet.getInt("numberOfEmployees");
        int service = resultSet.getInt("service");

        return new RestaurantResponse(id, name, location, restType, numberOfEmployees, service);
    };

    public List<RestaurantResponse> getAllRestaurant() {
        String sql = """
                SELECT
                id,
                name,
                location,
                rest_type,
                number_of_employees as numberOfEmployees,
                service FROM restaurants
                """;

        return jdbcTemplate.query(sql, restaurantRowMapper);
    }

    public RestaurantResponse getById(Long restaurantId){
        String sql = """
                SELECT
                id,
                name,
                location,
                rest_type,
                number_of_employees as numberOfEmployees,
                service FROM restaurants WHERE id = ?
                """;
        return jdbcTemplate.queryForObject(sql, restaurantRowMapper,restaurantId);
    }
}