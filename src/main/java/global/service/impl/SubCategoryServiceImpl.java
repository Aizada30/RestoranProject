package global.service.impl;

import global.dto.request.SubCategoryRequest;
import global.dto.response.CategoryGroupByResponse;
import global.dto.response.SimpleResponse;
import global.dto.response.SubCategoryResponse;
import global.entity.Category;
import global.entity.Subcategory;
import global.exceptionGlobal.NotFoundException;
import global.repo.CategoryRepo;
import global.repo.SubCategoryRepo;
import global.repo.dao.SubCategoryJDBCTemplate;
import global.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepo subCategoryRepo;
    private final SubCategoryJDBCTemplate subCategoryJDBCTemplate;
    private final CategoryRepo categoryRepo;

    @Override
    public SimpleResponse saveSucToCategory(Long categoryId, SubCategoryRequest subCategoryRequest) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(
                () -> new NotFoundException(String.format("SubCategory with id:%s  not found!", categoryId))
        );
        Subcategory subcategory = new Subcategory();
        subcategory.setName(subCategoryRequest.name());
        subcategory.setCategory(category);
        category.getSubcategory().add(subcategory);
        subCategoryRepo.save(subcategory);
        categoryRepo.save(category);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("SubCategory with id:%s successfully saved to category with id:%s ", subcategory.getId(), categoryId)
        );
    }

    @Override
    public List<SubCategoryResponse> getAllSubCategoryByCategoryId(Long categoryId) {
        return subCategoryJDBCTemplate.getAllSubCategoryWithCategoriesId(categoryId);
    }

    @Override
    public SubCategoryResponse getSubCategoryById(Long subCategoryId) {
        if (!subCategoryRepo.existsById(subCategoryId)) {
            log.error(String.format("SubCategory with id:%s not found or is exists", subCategoryId));
            throw new NotFoundException(String.format("SubCategory with id:%s not found or is exists", subCategoryId));
        }
        log.info(String.format("SubCategory with id:%s successfully foundet", subCategoryId));
        return subCategoryJDBCTemplate.getSubCategoryById(subCategoryId);
    }

    @Override
    public SimpleResponse deleteSubCategory(Long subCategoryId) {
        if (!subCategoryRepo.existsById(subCategoryId)) {
            log.error(String.format("SubCategory with id:%s not found", subCategoryId));
            throw new NotFoundException(String.format("SubCategory with id:%s not found", subCategoryId));
        }
        subCategoryRepo.deleteById(subCategoryId);
        log.info("SubCategory with id:%s successfully deleted");
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("SUbCategory with id:%s successfully deleted", subCategoryId)
        );
    }

    @Override
    public SimpleResponse updateSubCategory(Long subCategoryId, SubCategoryRequest subCategoryRequest) {
        Subcategory subcategory = subCategoryRepo.findById(subCategoryId).orElseThrow(
                (() -> {
                    log.error(String.format("Category with id:%s not found", subCategoryId));
                    throw new NotFoundException(
                            (String.format("Category with id:%s not found", subCategoryId)));
                }));
        subcategory.setName(subCategoryRequest.name());
        subCategoryRepo.save(subcategory);
        log.info(String.format("SubCategory with id:%s successfully updated", subCategoryId));

        return new SimpleResponse(
                HttpStatus.OK,
                String.format("SUbCategory with id:%s successfully deleted", subCategoryId)
        );
    }

    @Override
    public List<CategoryGroupByResponse> getAllSubCategories() {
        return subCategoryJDBCTemplate.getAllSubCategories();
    }
}
