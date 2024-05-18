package global.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class MenuPaginationResponse {
    private List<MenuResponse> menuResponseList;
    private int currentPage;
    private int pageSize;
}