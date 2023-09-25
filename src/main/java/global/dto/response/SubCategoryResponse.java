package global.dto.response;

import lombok.Builder;

/**
 * Abdyrazakova Aizada
 */
@Builder
public record SubCategoryResponse(
        Long id,
        String name,
        Long categoryId
) {

}
