package lt.techin.movie.review.dto.review;

import jakarta.validation.constraints.NotNull;
import lt.techin.movie.review.model.User;
import org.hibernate.validator.constraints.Range;

public record ReviewRequestDTO(
        @NotNull
        User user,
        @Range(min=1, max=10)
        int rating,
        String comment
) {
}
