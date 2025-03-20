package lt.techin.movie.review.service;

import lt.techin.movie.review.model.Review;
import lt.techin.movie.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review saveReview(Review review) {
       return reviewRepository.save(review);
    }
}
