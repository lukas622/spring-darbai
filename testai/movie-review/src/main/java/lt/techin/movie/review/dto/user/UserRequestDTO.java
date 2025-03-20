package lt.techin.movie.review.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.movie.review.model.Role;

import java.util.List;

public record UserRequestDTO(
        @NotNull
        @NotEmpty
        @Size(min = 4)
        @Pattern(regexp = "^[a-z]+$", message = "Genre must be letters and spaces only")
        String username,
        @NotNull
        @NotEmpty
        @Size(min = 6)
        String password,
        @NotNull
        List<Role> roles
) {
}
