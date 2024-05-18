package global.dto.request;

import global.validations.EmailValid;

public record SignInRequest(
        @EmailValid
        String email,
        String password
) {
}