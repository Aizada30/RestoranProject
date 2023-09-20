package global.dto.request;

import global.entity.enums.Role;
import global.validations.EmailValid;
import global.validations.PasswordValid;
import global.validations.PhoneNumberValid;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.time.LocalDate;

/**
 * Abdyrazakova Aizada
 */
public record SignUpRequest(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        LocalDate dateOfBirth,
        @EmailValid
        String email,
        @PasswordValid
        String password,
        @PhoneNumberValid
        String phoneNumber,
        @Enumerated(EnumType.STRING)
        Role role,
        LocalDate experience
) {

}
