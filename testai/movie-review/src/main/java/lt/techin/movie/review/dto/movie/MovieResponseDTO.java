package lt.techin.movie.review.dto.movie;

import jakarta.validation.constraints.NotNull;

public record MovieResponseDTO(
        long id,
        String title,
        int releaseYear,
        String genre
) {
}
