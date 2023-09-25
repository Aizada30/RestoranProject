package global.service.impl;

import global.dto.request.MenuRequest;
import global.dto.response.MenuResponse;
import global.dto.response.SearchResponse;
import global.dto.response.SimpleResponse;
import global.entity.Menu;
import global.entity.Restaurant;
import global.entity.Subcategory;
import global.exceptionGlobal.NotFoundException;
import global.repo.MenuRepo;
import global.repo.RestaurantRepo;
import global.repo.SubCategoryRepo;
import global.repo.dao.MenuJDBCTemplate;
import global.service.MenuService;
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
public class MenuServiceImpl implements MenuService {

    private final MenuRepo menuRepo;
    private final MenuJDBCTemplate menuJDBCTemplate;
    private final RestaurantRepo restaurantRepo;
    private final SubCategoryRepo subCategoryRepo;

    @Override
    public SimpleResponse saveMenu(Long restaurantId, MenuRequest menuRequest, Long subCategoryId) {
        Menu menu = new Menu();
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(
                () -> {
                    log.error(String.format("Menu with id:%s not found", menu.getId()));
                    return new NotFoundException(String.format("Manu with id:%s not found", menu.getId()));
                }
        );
        Subcategory subcategory = subCategoryRepo.findById(subCategoryId).orElseThrow(
                () -> {
                    log.error(String.format("SubCategory with id:%s not found", subCategoryId));
                    return new NotFoundException(String.format("SubCategory with id:%s not found", subCategoryId));
                }
        );
        menu.setName(menuRequest.name());
        menu.setPrice(menuRequest.price());
        menu.setImage(menuRequest.image());
        menu.setDescription(menuRequest.description());
        menu.setVegetarian(menuRequest.isVegetarian());
        menu.setRestaurant(restaurant);
        menu.setSubcategory(subcategory);
        subcategory.getMenuList().add(menu);
        restaurant.getMenultemList().add(menu);
        menuRepo.save(menu);
        log.info(String.format("Restaurant with id:%s successfully saved", restaurantId));
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Restaurant with id:%s successfully saved", restaurantId)
        );
    }

    @Override
    public List<MenuResponse> getAll() {
      return menuJDBCTemplate.getAll();
    }

    @Override
    public MenuResponse getById(Long menuId) {
        return menuJDBCTemplate.getById(menuId);
    }

    @Override
    public SimpleResponse deleteMenu(Long menuId) {
        if (!menuRepo.existsById(menuId)) {
            log.error(String.format("Menu with id:%s not found", menuId));
            throw new NotFoundException(String.format("Menu with id:%s not found", menuId));
        }
        menuRepo.deleteById(menuId);
        log.info(String.format("Menu with id:%s successfully deleted", menuId));
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Menu with id:%s successfully deleted", menuId)
        );
    }

    @Override
    public SimpleResponse updateMenu(Long menuId, MenuRequest menuRequest) {
        Menu menu = menuRepo.findById(menuId).orElseThrow(
                () -> {
                    log.error(String.format("Menu with id:%s not found", menuId));
                    return new NotFoundException(String.format("Menu with id:%s not found", menuId));
                }
        );
        menu.setName(menuRequest.name());
        menu.setPrice(menuRequest.price());
        menu.setImage(menuRequest.image());
        menu.setDescription(menuRequest.description());
        menu.setVegetarian(menuRequest.isVegetarian());
        menuRepo.save(menu);
        log.info(String.format("New menu with id: %s successfully saved", menuId));
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("New menu with id: %s successfully saved", menuId));
    }

    @Override
    public List<SearchResponse> search(String word) {
        return menuJDBCTemplate.searchMenu(word);
    }

    @Override
    public List<MenuResponse> sortByPrice(String word) {
        if(word.equalsIgnoreCase("asc") || word.equalsIgnoreCase("desc")){
            return menuJDBCTemplate.sortByPrice(word);
        }
        throw new NotFoundException("I don’t understand how to display your request Please enter it correctly");
    }

    @Override
    public List<MenuResponse> filter(String word) {
        if (word.equalsIgnoreCase("true") || word.equalsIgnoreCase("false")) {
            return menuJDBCTemplate.filterByBoo(word);
        }
        throw new NotFoundException("Food and category with this name is NOT FOUND ");
    }
}
