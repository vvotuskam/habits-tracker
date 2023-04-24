package kz.team.habittracker.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationRequest {
    @NotEmpty(message = "Firstname should not be empty")
    @Size(min = 1, max = 50, message = "Firstname should contain at least one character")
    private String firstname;

    @NotEmpty(message = "Lastname should not be empty")
    @Size(min = 1, max = 50, message = "Lastname should contain at least one character")
    private String lastname;

    @Email(message = "Invalid email")
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8, max = 50, message = "Password should contain at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "Password must contain 8 characters, at least 1 uppercase, 1 lowercase letter, 1 number")
    private String password;
}
