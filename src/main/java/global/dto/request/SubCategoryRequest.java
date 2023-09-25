package global.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * Abdyrazakova Aizada
 */
public record SubCategoryRequest(
        @NotNull
        String name

) {
}
