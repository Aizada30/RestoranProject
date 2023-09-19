package global.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * Abdyrazakova Aizada
 */
public record CategoryRequest(
        @NotNull
        String name
) {
}
