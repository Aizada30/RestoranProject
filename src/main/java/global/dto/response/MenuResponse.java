package global.dto.response;

import lombok.Builder;
import java.math.BigDecimal;

/**
 * Abdyrazakova Aizada
 */
@Builder
public record MenuResponse(
        Long id,
        String name,
        String image,
        BigDecimal price,
        String description,
        boolean isVegetarian) {

}
