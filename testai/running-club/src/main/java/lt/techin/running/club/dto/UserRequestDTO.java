package lt.techin.running.club.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.running.club.model.Role;

import java.util.List;

public record UserRequestDTO(
        @NotNull @Pattern(regexp = "^[a-z][0-9]+$") @Size(min = 4) String username,
        @NotNull @Size(min = 6) String password, @NotNull List<Role> roles) {
}
