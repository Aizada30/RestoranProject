package global.api;

import global.dto.request.MenuRequest;
import global.dto.response.MenuResponse;
import global.dto.response.SearchResponse;
import global.dto.response.SimpleResponse;
import global.service.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manu")
@Tag(name = "MANULTEM-API")
public class MenuApi {
    private final MenuService menuService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public SimpleResponse save(@RequestBody MenuRequest menuRequest, @RequestParam Long restaurantId, @RequestParam Long subCategoryId){
        return menuService.saveMenu(restaurantId,menuRequest,subCategoryId );
    }

     @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public List<MenuResponse> getAll(){
        return menuService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getById")
    public MenuResponse getById(@RequestParam Long menuId){
        return menuService.getById(menuId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public SimpleResponse delete(@RequestParam Long menuId){
        return menuService.deleteMenu(menuId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
    public SimpleResponse update(@RequestParam Long menuId,@RequestBody MenuRequest menuRequest){
        return menuService.updateMenu(menuId,menuRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/search")
    public List<SearchResponse> search(@RequestParam String word){
        return menuService.search(word);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/sortByPrice")
    public List<MenuResponse> sortByPrice(@RequestParam String word){
        return menuService.sortByPrice(word);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/filter")
    public List<MenuResponse> filter(@RequestParam String word){
        return menuService.filter(word);
    }


}
