package global.service.impl;

import global.dto.request.CategoryRequest;
import global.dto.response.CategoryResponse;
import global.dto.response.SimpleResponse;
import global.repo.CategoryRepo;
import global.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo ;

    @Override
    public SimpleResponse saveCategory(CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        return null;
    }

    @Override
    public CategoryResponse getByIdCategory(Long categoryId) {
        return null;
    }

    @Override
    public SimpleResponse deleteCategory(Long categoryId) {
        return null;
    }

    @Override
    public SimpleResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        return null;
    }
}
