package global.dto.response;

import java.math.BigDecimal;

public record WholeMenu(
        Long id,
        String categoryName,
        String subCategoryName,
        String name,
        BigDecimal price
) {
}