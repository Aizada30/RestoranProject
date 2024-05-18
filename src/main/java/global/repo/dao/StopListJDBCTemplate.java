package global.repo.dao;

import global.dto.response.StopListPagination;
import global.dto.response.StopListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StopListJDBCTemplate {

    private final JdbcTemplate jdbcTemplate;

    public List<StopListResponse> getAllStopLists(Long restId) {
        String sql = """
                SELECT 
                id,
                date,
                reason,
                menu_id 
                FROM stop_lists WHERE id = ?
                """;
        return jdbcTemplate.query(sql, (resultSet, rowNumber) -> {
            return new StopListResponse(
                    resultSet.getLong("id"),
                    LocalDate.parse(resultSet.getString("date")),
                    resultSet.getString("reason"),
                    resultSet.getLong("menu_id")
            );
        }, restId);
    }

    public StopListResponse getStopListById(Long stopListId) {
        String sql = """
                SELECT 
                id,
                date,
                reason,
                menu_id 
                FROM stop_lists WHERE id = ?
                """;
        return jdbcTemplate.queryForObject(sql, (resultSet, rowNumber) -> {
            return new StopListResponse(
                    resultSet.getLong("id"),
                    LocalDate.parse(resultSet.getString("date")),
                    resultSet.getString("reason"),
                    resultSet.getLong("menu_id")
            );
        },stopListId);
    }

    public StopListPagination getAll(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = """
                SELECT 
                id,
                date,
                reason,
                menu_id 
                FROM stop_lists 
                LIMIT ? OFFSET ?
                """;
        List<StopListResponse> stopListResponses = jdbcTemplate.query(sql, (resultSet, rowNumber) ->
             new StopListResponse(
                    resultSet.getLong("id"),
                    LocalDate.parse(resultSet.getString("date")),
                    resultSet.getString("reason"),
                    resultSet.getLong("menu_id")
            ),
                pageSize,offset
        );
        return StopListPagination
                .builder()
                .page(page)
                .pageSize(pageSize)
                .stopListResponses(stopListResponses)
                .build();
    }
}