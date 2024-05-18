package global.dto.response;

import global.entity.enums.Role;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String email,
        String phoneNumber,
        LocalDate experience,
        Role role
) {
    public UserResponse(Long id, String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber,LocalDate experience, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
        this.role = role;
    }
}