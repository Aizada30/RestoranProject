package global.api;

import global.dto.request.SubCategoryRequest;
import global.dto.response.CategoryGroupByResponse;
import global.dto.response.SimpleResponse;
import global.dto.response.SubCategoryResponse;
import global.service.SubCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subCategory")
@Tag(name = "SUBCATEGORY-API")
public class SubCategoryApi {

    private final SubCategoryService subCategoryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public SimpleResponse saveSubCategory(@RequestParam @Valid Long categoryId, @RequestBody SubCategoryRequest subCategoryRequest){
        return subCategoryService.saveSucToCategory(categoryId, subCategoryRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllById")
    public List<SubCategoryResponse> getAllByCategoryId(@RequestParam Long categoryId){
        return subCategoryService.getAllSubCategoryByCategoryId(categoryId);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getById")
    public SubCategoryResponse getById(@RequestParam Long subCategoryId){
        return subCategoryService.getSubCategoryById(subCategoryId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public SimpleResponse deleteSubCategory(@RequestParam Long subCategoryId){
        return subCategoryService.deleteSubCategory(subCategoryId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
    public SimpleResponse updateSubCategory(@RequestParam Long subCategoryId,@RequestBody SubCategoryRequest subCategoryRequest){
        return subCategoryService.updateSubCategory(subCategoryId,subCategoryRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public List<CategoryGroupByResponse> getAllSubCategories(){
        return subCategoryService.getAllSubCategories();
    }


}
