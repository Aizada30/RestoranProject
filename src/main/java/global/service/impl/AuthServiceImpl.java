package global.service.impl;

import global.dto.request.SignInRequest;
import global.dto.request.SignUpRequest;
import global.dto.response.AuthResponse;
import global.entity.User;
import global.exceptionGlobal.AlreadyException;
import global.exceptionGlobal.BadCredentialException;
import global.exceptionGlobal.NotFoundException;
import global.repo.UserRepo;
import global.security.jwt.JwtService;
import global.service.AuthService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static global.entity.enums.Role.ADMIN;

/**
 * Abdyrazakova Aizada
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email())) {
            throw new AlreadyException(
                    "Already exists user with email " + signUpRequest.email());
        }
        User user = User.builder()
                .firstName(signUpRequest.firstName())
                .lastName(signUpRequest.lastName())
                .dateOfBirth(signUpRequest.dateOfBirth())
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .phoneNumber(signUpRequest.phoneNumber())
                .role(signUpRequest.role())
                .experience(signUpRequest.experience())
                .build();
        userRepository.save(user);
        log.info("User successfully saved");
        // token generate method
        String token = jwtService.generateToken(user);
        log.info("Users token successfully generated");
        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.email())
                .orElseThrow(
                        () -> new NotFoundException(
                                "User with email: " + signInRequest.email() + " doesn't exists!"));
        if (signInRequest.email().isBlank()) {
            log.error("Email is blank");
            throw new BadCredentialException("Email is blank!");
        }
        if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())) {
            log.error("Wrong password");
            throw new BadCredentialException("Wrong password!");
        }
        String token = jwtService.generateToken(user);
        log.info("Token successfully generated");
        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @PostConstruct
    public void initSaveAdmin() {
        User user = User.builder()
                .firstName("admin")
                .lastName("admins lastName")
                .dateOfBirth(LocalDate.now())
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("123456"))
                .phoneNumber("+996558870024")
                .role(ADMIN)
                .experience(null)
                .build();
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
        log.info("Admin successfully created and saved");
    }
}
