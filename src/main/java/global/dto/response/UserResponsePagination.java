package global.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder
@Getter
public class UserResponsePagination {
    int currentPage;
    private int pageSize;
    private List<UserResponse> userResponseList;
}