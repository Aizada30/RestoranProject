package global.api;

import global.dto.request.SignInRequest;
import global.dto.request.SignUpRequest;
import global.dto.response.AuthResponse;
import global.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "AUTH-API")
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/signUp")
    public AuthResponse signUp( @RequestBody @Valid SignUpRequest signUpRequest){
       return authService.signUp(signUpRequest);
    }

    @PostMapping("/signIn")
    public AuthResponse signIn( @RequestBody @Valid SignInRequest signInRequest){
       return authService.signIn(signInRequest);
    }
}