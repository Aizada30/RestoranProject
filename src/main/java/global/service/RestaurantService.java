package global.service;

import global.dto.request.RestaurantRequest;
import global.dto.response.RestaurantResponse;
import global.dto.response.SimpleResponse;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */
public interface RestaurantService {
    SimpleResponse saveRestaurant (RestaurantRequest restaurantRequest);
    List<RestaurantResponse> getAll();
    RestaurantResponse getRestaurantById(Long restaurantId);
    SimpleResponse deleteRestaurant(Long restaurantId);
    SimpleResponse updateRestaurant(Long restaurantId,RestaurantRequest restaurantRequest);

}
