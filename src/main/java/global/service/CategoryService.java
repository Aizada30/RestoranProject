package global.service;

import global.dto.request.CategoryRequest;
import global.dto.response.CategoryPaginationResponse;
import global.dto.response.CategoryResponse;
import global.dto.response.SimpleResponse;

public interface CategoryService {
    SimpleResponse saveCategory(CategoryRequest categoryRequest);
    CategoryPaginationResponse getAllCategory(int page,int pageSize);
    CategoryResponse getByIdCategory (Long categoryId);
    SimpleResponse deleteCategory(Long categoryId);
    SimpleResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);
}
