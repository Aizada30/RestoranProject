package global.dto.response;

import global.entity.enums.Role;
import lombok.Builder;

/**
 * Abdyrazakova Aizada
 */
@Builder
public record AuthResponse(
        String token,
        String email,
        Role role
) {
}
