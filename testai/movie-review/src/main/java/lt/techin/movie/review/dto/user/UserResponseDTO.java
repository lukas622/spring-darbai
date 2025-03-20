package lt.techin.movie.review.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lt.techin.movie.review.model.Role;

import java.util.List;

public record UserResponseDTO(
        @NotNull
        long id,
        @NotNull
        @NotEmpty
        String username,
        @NotNull
        List<Role> roles
) {
}
