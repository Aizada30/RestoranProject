package global.dto.response;

import lombok.Builder;

/**
 * Abdyrazakova Aizada
 */
@Builder
public record AverageSumResponse(
        int sum
) {
    public AverageSumResponse {
    }
}
