package global.service;

import global.dto.request.StopListRequest;
import global.dto.response.SimpleResponse;
import global.dto.response.StopListPagination;
import global.dto.response.StopListResponse;
import java.util.List;

public interface StopListService {
    SimpleResponse saveStopList(Long menuId, StopListRequest stopListRequest);
    StopListResponse getStopListById(Long stopListId);
    List<StopListResponse> getAllStopLists(Long restId);
    SimpleResponse deleteStopList(Long stopListId);
    SimpleResponse updateStopList(Long stopListId,StopListRequest stopListRequest);
    StopListPagination getAll (int page, int pageSize);
}