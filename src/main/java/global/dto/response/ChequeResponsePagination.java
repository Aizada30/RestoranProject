package global.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder
@Getter
public class ChequeResponsePagination {
    private int currentPage;
    private int pageSize;
    private List<ChequeResponse> chequeResponseList;}