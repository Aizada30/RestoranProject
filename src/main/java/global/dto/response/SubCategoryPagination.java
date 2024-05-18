package global.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder
@Getter
public class SubCategoryPagination {
    private int currentPage;
    private int pageSize;
    private List<CategoryGroupByResponsePag> categoryGroupByResponsePagList;
}