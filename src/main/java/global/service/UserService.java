package global.service;

import global.dto.request.SignUpRequest;
import global.dto.response.SimpleResponse;
import global.dto.response.UserResponse;
import global.dto.response.UserResponsePagination;

public interface UserService {
    SimpleResponse saveUser(Long restaurantId, SignUpRequest signUpRequest);
    SimpleResponse acceptOrRejectUser(Long userId, String word, Long restaurantId);
    UserResponsePagination getAll(int currentPage,int pageSize);
    UserResponse getById(Long userId);
    SimpleResponse deleteUser(Long userId);
    SimpleResponse updateUser(Long userId, SignUpRequest signUpRequest);
}
