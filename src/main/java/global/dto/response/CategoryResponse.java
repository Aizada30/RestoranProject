package global.dto.response;

import lombok.Builder;

/**
 * Abdyrazakova Aizada
 */
@Builder
public record CategoryResponse(
        Long id,
        String name
) {

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
