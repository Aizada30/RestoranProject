package global.dto.response;

import lombok.Builder;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */
@Builder
public record CategoryGroupByResponse(
        Long categoryId,
        String name,
        List<String> names

) {
}
