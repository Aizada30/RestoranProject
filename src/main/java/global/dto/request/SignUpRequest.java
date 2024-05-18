package global.dto.request;

import global.entity.enums.Role;
import global.validations.PasswordValid;
import global.validations.PhoneNumberValid;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record SignUpRequest(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        LocalDate dateOfBirth,
        @Email(message = "Email is invalid")
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