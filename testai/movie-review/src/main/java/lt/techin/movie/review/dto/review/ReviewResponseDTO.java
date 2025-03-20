package lt.techin.movie.review.dto.review;

public record ReviewResponseDTO(
        long id,
        long userId,
        long movieId,
        int rating,
        String comment
) {
}
