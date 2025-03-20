package lt.techin.movie.review.dto.review;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lt.techin.movie.review.model.User;

public record ReviewRequestDTO(
        @NotNull
        User user,
        @Size(min = 1, max = 10)
        int rating,
        String comment
) {
}
