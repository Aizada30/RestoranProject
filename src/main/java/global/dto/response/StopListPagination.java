package global.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder
@Getter
public class StopListPagination {

    private int page;

    private int pageSize;

    private List<StopListResponse> stopListResponses;
}