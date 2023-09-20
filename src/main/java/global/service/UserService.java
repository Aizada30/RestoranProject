package global.service;

import global.dto.request.SignUpRequest;
import global.dto.response.SimpleResponse;
import global.dto.response.UserResponse;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */
public interface UserService {

    List<UserResponse> getAll();
    UserResponse getById(Long userId);
    SimpleResponse deleteUser (Long userId);
    SimpleResponse updateUser(Long userId, SignUpRequest signUpRequest);


}
