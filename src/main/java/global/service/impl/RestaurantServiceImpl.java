package global.service.impl;

import global.dto.request.RestaurantRequest;
import global.dto.response.RestaurantResponse;
import global.dto.response.SimpleResponse;
import global.entity.Restaurant;
import global.exceptionGlobal.NotFoundException;
import global.repo.RestaurantRepo;
import global.repo.dao.RestaurantJDBCTemplate;
import global.service.RestaurantService;
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
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepo restaurantRepo;
    private final RestaurantJDBCTemplate restaurantJDBCTemplate;

    @Override
    public SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.name());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setService(restaurantRequest.service());
        restaurant.setRestType(restaurantRequest.restType());
        restaurantRepo.save(restaurant);
        return new SimpleResponse(
                HttpStatus.OK,
                ("New restaurant successfully saved")
        );
    }

    @Override
    public List<RestaurantResponse> getAll() {
        return restaurantJDBCTemplate.getAllRestaurant();
    }

    @Override
    public RestaurantResponse getRestaurantById(Long restaurantId) {
        return restaurantJDBCTemplate.getById(restaurantId);
    }

    @Override
    public SimpleResponse deleteRestaurant(Long restaurantId) {
        if (!restaurantRepo.existsById(restaurantId)) {
            log.error(String.format("Restaurant with id:%s is exists", restaurantId));
            throw new NotFoundException(String.format("Restaurant with id:%s is exists", restaurantId));
        }
        restaurantRepo.deleteById(restaurantId);
        log.info(String.format("Restaurant with id:%s successfully deleted",restaurantId));
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Restaurant with id:%s successfully deleted",restaurantId)
        );
    }

    @Override
    public SimpleResponse updateRestaurant(Long restaurantId, RestaurantRequest restaurantRequest) {
       Restaurant restaurant= restaurantRepo.findById(restaurantId).orElseThrow(
                ()->new NotFoundException(
                        String.format("Restaurant with id:%s not found ",restaurantId)
                )
        );
       restaurant.setName(restaurantRequest.name());
       restaurant.setLocation(restaurantRequest.location());
       restaurant.setService(restaurantRequest.service());
       restaurant.setRestType(restaurantRequest.restType());
       restaurantRepo.save(restaurant);
       log.info(String.format("Restaurant with id:%s successfully updated",restaurantId));
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Restaurant with id:%s successfully updated",restaurantId)
        );
    }
}
