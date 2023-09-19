package global.dto.response;

import global.entity.enums.Role;
import lombok.Builder;

import java.time.LocalDate;

/**
 * Abdyrazakova Aizada
 */
@Builder
public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String email,
        String phoneNumber,
        Role role
) {
    public UserResponse(Long id, String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
