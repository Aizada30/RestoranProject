package global.service;

import global.dto.request.CategoryRequest;
import global.dto.response.CategoryResponse;
import global.dto.response.SimpleResponse;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */

public interface CategoryService {
    SimpleResponse saveCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> getAllCategory();
    CategoryResponse getByIdCategory (Long categoryId);
    SimpleResponse deleteCategory(Long categoryId);
    SimpleResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);
}
