package global.repo.dao;

import global.dto.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */

@Repository
@RequiredArgsConstructor
public class CategoryJDBCTemplate {

    private final JdbcTemplate jdbcTemplate;

    public List<CategoryResponse> getAllCategory() {
        String sql = """
                SELECT id,
                name FROM categories
                """;
        return jdbcTemplate.query(sql, (resultSet, rowNumber) -> {
            return new CategoryResponse(
                    resultSet.getLong("id"),
                    resultSet.getString("name")
            );
        });
    }

    public CategoryResponse getCategoryById(Long categoryId) {
        String sql = "SELECT id, name FROM categories WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return new CategoryResponse(
                    rs.getLong("id"),
                    rs.getString("name"));
        }, categoryId);
    }


//    public CategoryResponse getCategoryById(Long categoryId) {
//        String sql = "SELECT id, name FROM categories WHERE id = ?";
//        CategoryResponse categoryResponse = jdbcTemplate.queryForObject(sql,
//                (resultSet, rowNum) -> {
//                    long id = resultSet.getLong("id");
//                    String name = resultSet.getString("name");
//                    return new CategoryResponse(id, name);
//                },
//                categoryId);
//
//        return categoryResponse;
//    }


}
