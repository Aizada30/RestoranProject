package global.service;

import global.dto.request.SubCategoryRequest;
import global.dto.response.CategoryGroupByResponse;
import global.dto.response.SimpleResponse;
import global.dto.response.SubCategoryResponse;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */
public interface SubCategoryService {
    SimpleResponse saveSucToCategory(Long categoryId, SubCategoryRequest subCategoryRequest);
    List<SubCategoryResponse> getAllSubCategoryByCategoryId(Long categoryId);
    SubCategoryResponse getSubCategoryById(Long subCategoryId);
    SimpleResponse deleteSubCategory(Long subCategoryId);
    SimpleResponse updateSubCategory(Long subCategoryId,SubCategoryRequest subCategoryRequest);
    List<CategoryGroupByResponse> getAllSubCategories();
}
