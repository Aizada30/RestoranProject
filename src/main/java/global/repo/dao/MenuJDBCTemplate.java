package global.repo.dao;

import global.dto.response.MenuPaginationResponse;
import global.dto.response.MenuResponse;
import global.dto.response.SearchResponse;
import global.dto.response.WholeMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuJDBCTemplate {

    private final JdbcTemplate jdbcTemplate;

        private final RowMapper<MenuResponse> menuResponseRowMapper = (resultSet, rowNumber) -> {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String image = resultSet.getString("image");
            BigDecimal price = resultSet.getBigDecimal("price");
            String description = resultSet.getString("description");
            boolean isVegetarian = resultSet.getBoolean("is_vegetarian");

            return new MenuResponse(id, name, image, price, description, isVegetarian);
        };

        private final RowMapper<WholeMenu> wholeMenuRow = (resultSet, rowNumber) -> {
            Long id = resultSet.getLong("id");
            String categoryName = resultSet.getString("categoryName");
            String subCategoryName = resultSet.getString("subCategoryName");
            String menuName = resultSet.getString("menuName");
            BigDecimal price = resultSet.getBigDecimal("price");


            return new WholeMenu(id, categoryName, subCategoryName, menuName, price);
        };



    public MenuPaginationResponse getAll(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = """
                SELECT 
                id,
                name,
                image,
                price,
                description,
                is_vegetarian
                FROM menues 
                LIMIT ? OFFSET ?
                """;
        List<MenuResponse> menuResponseList = jdbcTemplate.query(sql, (resultSet, rowNumber) ->
                        new MenuResponse(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("image"),
                                resultSet.getBigDecimal("price"),
                                resultSet.getString("description"),
                                resultSet.getBoolean("is_vegetarian")
                        ),
                pageSize, offset
        );
        return MenuPaginationResponse
                .builder()
                .currentPage(page)
                .pageSize(pageSize)
                .menuResponseList(menuResponseList)
                .build();
}

    public List<MenuResponse> filterByBoo(String word) {
        if (word.equalsIgnoreCase("false")) {
            String sql = """
                    SELECT 
                    id,
                    name,
                    image,
                    price,
                    description,
                    is_vegetarian
                    FROM menues WHERE is_vegetarian IN (false) 
                    """;
            return jdbcTemplate.query(sql, menuResponseRowMapper);
        } else if (word.equalsIgnoreCase("true")) {
            String sql = """
                    SELECT 
                    id,
                    name,
                    image,
                    price,
                    description,
                    is_vegetarian
                    FROM menues WHERE is_vegetarian IN (true) 
                    """;
            return jdbcTemplate.query(sql, menuResponseRowMapper);
        }
        return null;
    }


    public List<MenuResponse> sortByPrice(String word) {
        if (word.equalsIgnoreCase("asc")) {
            String sql = """
                    SELECT 
                    id,
                    name,
                    image,
                    price,
                    description,
                    is_vegetarian
                    FROM menues ORDER BY price ASC 
                    """;
            return jdbcTemplate.query(sql, menuResponseRowMapper);
        } else if (word.equalsIgnoreCase("desc")) {
            String sql = """
                    SELECT 
                    id,
                    name,
                    image,
                    price,
                    description,
                    is_vegetarian
                    FROM menues ORDER BY price DESC 
                    """;
            return jdbcTemplate.query(sql, menuResponseRowMapper);
        }
        return null;
    }


    public MenuResponse getById(Long menuId) {
        String sql = """
                SELECT 
                id,
                name,
                image,
                price,
                description,
                is_vegetarian
                FROM menues WHERE id = ?
                """;
        return jdbcTemplate.queryForObject(sql, menuResponseRowMapper, menuId);
    }


    private final RowMapper<SearchResponse> menuSearch = (resultSet, rowNumber) -> {
        Long id = resultSet.getLong("id");
        String scName = resultSet.getString("subcategory_name");
        String cName = resultSet.getString("category_name");
        String mName = resultSet.getString("menu_name");
        String image = resultSet.getString("image");
        BigDecimal price = resultSet.getBigDecimal("price");
        String description = resultSet.getString("description");
        boolean isVegetarian = resultSet.getBoolean("is_vegetarian");

        return new SearchResponse(id, scName, cName, mName, image, price, description, isVegetarian);
    };

    public List<SearchResponse> searchMenu(String word) {
        String sql = """
                    SELECT
                    m.id,
                    sc.name AS subcategory_name,
                    cat.name AS category_name,
                    m.name AS menu_name,
                    m.image,
                    m.price,
                    m.description,
                    m.is_vegetarian
                        
                    FROM menues m
                    FULL JOIN subcategories AS sc ON m.subcategory_id = sc.id
                    FULL JOIN categories AS cat ON sc.category_id = cat.id
                    WHERE m.name ILIKE ? OR cat.name ILIKE ? OR sc.name ILIKE ?
                """;

        return jdbcTemplate.query(sql, menuSearch, "%" + word + "%", "%" + word + "%", "%" + word + "%");
    }

    public List<WholeMenu> getAllMenuByChequeId(Long chequeId) {
        String sql = """
                SELECT 
                m.id,
                c.name,
                sc.name,
                m.name,
                m.price
                FROM cheques_menu_list AS mc 
                JOIN menues AS m ON mc.menu_list_id = m.id
                JOIN subcategories AS sc ON m.subcategory_id = sc.id
                JOIN categories AS c ON sc.category_id = c.id
                WHERE mc.cheque_list_id = ?
                """;
        return jdbcTemplate.query(sql, wholeMenuRow, chequeId);
    }
}