package global.dto.request;

import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotNull
        String name
) {
}