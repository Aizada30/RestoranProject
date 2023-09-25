package global.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */
@Getter
@Builder
@Setter
@AllArgsConstructor
public class MenuPaginationResponse {
    private List<MenuResponse> menuResponseList;
    private int currentPage;
    private int pageSize;
}
