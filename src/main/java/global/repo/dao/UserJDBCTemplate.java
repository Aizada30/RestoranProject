package global.repo.dao;

import global.dto.response.CategoryResponse;
import global.dto.response.RestaurantResponse;
import global.dto.response.UserResponse;
import global.entity.enums.RestType;
import global.entity.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Abdyrazakova Aizada
 */
@Repository
@RequiredArgsConstructor
public class UserJDBCTemplate {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<UserResponse> userRowMapper = (resultSet, rowNumber) -> {
        Long id = resultSet.getLong("id");

        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");

        String dateOfBirthString = resultSet.getString("date_of_birth");
        LocalDate dateOfBirth = (dateOfBirthString != null) ? LocalDate.parse(dateOfBirthString) : null;

        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phone_number");

        String experienceString = resultSet.getString("experience");
        LocalDate experience = (experienceString != null) ? LocalDate.parse(experienceString) : null;

        String roleString = resultSet.getString("role");
        Role role = (roleString != null) ? Role.valueOf(roleString) : null;

        return new UserResponse(id, firstName, lastName, dateOfBirth, email, phoneNumber, experience, role);
};


    public List<UserResponse> getAllUsers() {
        String sql = """
                SELECT id,
                       first_name,
                       last_name,
                       date_of_birth,
                       email,
                       phone_number,
                       experience,
                       role
                FROM users
                """;
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public UserResponse getUserById(Long userId){
        String sql = """
                SELECT id,
                       first_name,
                       last_name,
                       date_of_birth,
                       email,
                       phone_number,
                       experience,
                       role
                FROM users WHERE id = ?
                """;
        return jdbcTemplate.queryForObject(sql,userRowMapper,userId);
    }


}
