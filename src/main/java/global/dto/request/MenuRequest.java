package global.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

/**
 * Abdyrazakova Aizada
 */
public record MenuRequest(
        @NotNull
        String name,
        String image,
        @Column(nullable = false)
        BigDecimal price,
        String description,
        boolean isVegetarian
) {

}
