package global.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Abdyrazakova Aizada
 */

@Builder
public record ChequeResponse(
        Long id,
        String waiterFullName,
        List<Long> items,
        BigDecimal averagePrice,
        BigDecimal total,
        LocalDate date) {
    public ChequeResponse(Long id, String waiterFullName, List<Long> items, BigDecimal averagePrice, BigDecimal total, LocalDate date) {
        this.id = id;
        this.waiterFullName = waiterFullName;
        this.items = items;
        this.averagePrice = averagePrice;
        this.total = total;
        this.date = date;
    }

}




