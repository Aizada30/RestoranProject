package global.dto.request;

import java.time.LocalDate;

/**
 * Abdyrazakova Aizada
 */
public record StopListRequest(
        LocalDate date,
        String reason
) {
}
