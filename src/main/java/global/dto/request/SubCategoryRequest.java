package global.dto.request;

import jakarta.validation.constraints.NotNull;

public record SubCategoryRequest(
        @NotNull
        String name
) {
}