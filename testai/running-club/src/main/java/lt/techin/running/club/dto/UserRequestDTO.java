package lt.techin.running.club.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.running.club.model.Registration;
import lt.techin.running.club.model.Role;

import java.util.List;

public record UserRequestDTO(@NotNull long id,
                             @NotNull @Pattern(regexp = "^[a-z0-9_.-]*$") @Size(min = 4) String username,
                             @NotNull @Size(min = 6) String password,
                             List<Registration> registrations, List<Role> roles) {
}
