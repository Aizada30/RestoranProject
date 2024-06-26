package global.service;

import global.dto.request.MenuRequest;
import global.dto.response.MenuPaginationResponse;
import global.dto.response.MenuResponse;
import global.dto.response.SearchResponse;
import global.dto.response.SimpleResponse;
import java.util.List;

public interface MenuService {

    SimpleResponse saveMenu(Long restaurantId, MenuRequest menuRequest,Long subCategoryId);
    MenuPaginationResponse getAll(int page, int pageSize);
    MenuResponse getById(Long menuId);
    SimpleResponse deleteMenu(Long menuId);
    SimpleResponse updateMenu(Long menuId , MenuRequest menuRequest);
    List<SearchResponse> search (String word);
    List<MenuResponse>sortByPrice(String word);
    List<MenuResponse>filter(String word);
}