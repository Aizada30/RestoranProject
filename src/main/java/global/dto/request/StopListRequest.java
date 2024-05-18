package global.dto.request;

import java.time.LocalDate;

public record StopListRequest(
        LocalDate date,
        String reason
) {
}