package lt.techin.movie.review.dto.review;

import lt.techin.movie.review.model.Review;

public class ReviewMapper {
    public static Review toReview(ReviewRequestDTO dto) {
        return new Review(dto.comment(), dto.rating(), dto.user());
    }

    public static ReviewResponseDTO toReviewResponseDTO(Review review) {
        return new ReviewResponseDTO(
                review.getId(),
                review.getUser().getId(),
                review.getMovie().getId(),
                review.getRating(),
                review.getComment()
        );
    }
}
