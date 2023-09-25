package global.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

/**
 * Abdyrazakova Aizada
 */
@Builder
public record SearchResponse(
        Long id,
        String sucCategoryName,
        String categoryName,
        String foodName,
        String image,
        BigDecimal price,
        String description,
        boolean isVegetarian

) {
}
