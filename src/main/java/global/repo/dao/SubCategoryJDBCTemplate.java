package global.repo.dao;

import global.dto.response.CategoryGroupByResponsePag;
import global.dto.response.SubCategoryPagination;
import global.dto.response.SubCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class SubCategoryJDBCTemplate {

    private final JdbcTemplate jdbcTemplate;

    public List<SubCategoryResponse> getAllSubCategoryWithCategoriesId(Long categoryId) {
        String sql = """
                SELECT
                id,
                name,
                category_id
                FROM subcategories WHERE category_id=?
                ORDER BY name
                """;
        return jdbcTemplate.query(sql, (resultSet, rowNumber) -> {
            return new SubCategoryResponse(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("category_id")
            );
        }, categoryId);
    }


    public SubCategoryResponse getSubCategoryById(Long subCategoryId) {
        String sql = """
                SELECT id,name,category_id FROM subcategories WHERE id = ?
                """;
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return new SubCategoryResponse(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getLong("category_id")
            );
        }, subCategoryId);
    }

    public SubCategoryPagination getAllSubCategories(int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;
        String sql = """      
                       SELECT 
                       c.id AS categoryId,
                       c.name AS categoryName,
                       array_agg(sub.name) AS names
                       FROM subcategories AS sub LEFT JOIN categories AS c ON sub.category_id=c.id GROUP BY  c.id, c.name  
                       LIMIT ? OFFSET ?                                                                                     
                """;
        List<CategoryGroupByResponsePag> categoryGroupByResponsePag = jdbcTemplate.query(sql, (resultSet, rowNumber) ->
             new CategoryGroupByResponsePag(
                    resultSet.getLong("categoryId"),
                    resultSet.getString("categoryName"),
                    Collections.singletonList(resultSet.getString("names"))),currentPage,offset);
        return SubCategoryPagination
                .builder()
                .currentPage(currentPage)
                .pageSize(pageSize)
                .categoryGroupByResponsePagList(categoryGroupByResponsePag)
                .build();
    }
}