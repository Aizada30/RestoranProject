package global.dto.response;

import lombok.Builder;

import java.time.LocalDate;

/**
 * Abdyrazakova Aizada
 */
@Builder
public record StopListResponse(
        Long id,
        LocalDate date,
        String reason,
        Long menu
) {
}
