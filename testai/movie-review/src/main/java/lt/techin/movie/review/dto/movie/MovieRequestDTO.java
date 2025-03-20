package lt.techin.movie.review.dto.movie;

import jakarta.validation.constraints.*;

public record MovieRequestDTO(
        @NotNull
        @NotEmpty
        @Size(min = 2)
        String title,
        @NotNull
        @Min(1900)
        int releaseYear,
        @NotNull
        @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Genre must be letters and spaces only")
        String genre
) {
}
