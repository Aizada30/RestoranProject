package global.service.impl;

import global.dto.request.CategoryRequest;
import global.dto.response.CategoryPaginationResponse;
import global.dto.response.CategoryResponse;
import global.dto.response.SimpleResponse;
import global.entity.Category;
import global.exceptionGlobal.NotFoundException;
import global.repo.CategoryRepo;
import global.repo.dao.CategoryJDBCTemplate;
import global.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryJDBCTemplate categoryJDBCTemplate;

    @Override
    public SimpleResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());
        categoryRepo.save(category);
        log.info(String.format("Category with id:%s successfully saved", category.getId()));
        return new SimpleResponse(
                HttpStatus.OK,
                "Category successfully saved"
        );
    }

    @Override
    public CategoryPaginationResponse getAllCategory(int page, int pageSize) {
        log.info("All categories exit");
        return categoryJDBCTemplate.getAllCategory(page,pageSize);
    }

    @Override
    public CategoryResponse getByIdCategory(Long categoryId) {
        if (!categoryRepo.existsById(categoryId)) {
            log.error(String.format("Category with id %s not found", categoryId));
            throw new NotFoundException(
                    String.format("Category with id %s not found", categoryId));
        }
        log.info(String.format("Category with id %s successfully get", categoryId));
        return categoryJDBCTemplate.getCategoryById(categoryId);
    }

    @Override
    public SimpleResponse deleteCategory(Long categoryId) {
        if (!categoryRepo.existsById(categoryId)) {
            log.error(String.format("Category with id %s not found", categoryId));
            throw new NotFoundException(
                    String.format("Category with id %s not found", categoryId));
        }
        categoryRepo.deleteById(categoryId);
        log.info(String.format("Category with id:%s successfully deleted", categoryId));
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Category with id: %s successfully deleted", categoryId)
        );
    }

    @Override
    public SimpleResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(
                () -> {
                    log.error(String.format("Category with id:%s not found", categoryId));
                    return new NotFoundException(
                            (String.format("Category with id:%s not found", categoryId)));
                }
        );
        category.setName(categoryRequest.name());
        log.info(String.format("Category with id:%s successfully updated", categoryId));
        categoryRepo.save(category);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Category with id:%s successfully updated", categoryId)
        );
    }
}