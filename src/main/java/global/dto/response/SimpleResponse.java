package global.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

/**
 * Abdyrazakova Aizada
 */
@Builder
public record SimpleResponse(
        HttpStatus httpStatus,
        String message
) {

}
