package lt.techin.movie.review.controller;

import jakarta.validation.Valid;
import lt.techin.movie.review.dto.review.ReviewMapper;
import lt.techin.movie.review.dto.review.ReviewRequestDTO;
import lt.techin.movie.review.dto.review.ReviewResponseDTO;
import lt.techin.movie.review.model.Movie;
import lt.techin.movie.review.model.Review;
import lt.techin.movie.review.model.User;
import lt.techin.movie.review.service.MovieService;
import lt.techin.movie.review.service.ReviewService;
import lt.techin.movie.review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;
    private final MovieService movieService;
    private final UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, MovieService movieService, UserService userService) {
        this.reviewService = reviewService;
        this.movieService = movieService;
        this.userService = userService;
    }

    @PostMapping("/movies/{movieId}/reviews")
    public ResponseEntity<?> addReview(@PathVariable long movieId, @Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
        if (movieService.findMovieById(movieId) == null) {
            return ResponseEntity.notFound().build();
        }

        User currentUser = userService.findById(reviewRequestDTO.user().getId());

        for (Review review : currentUser.getReviews()) {
            if (review.getMovie().getId() == movieId) {
                Map<String, String> response = new HashMap<>();
                response.put("movie", "you have already reviewed this movie");
                return ResponseEntity.badRequest().body(response);
            }
        }

        Review newreview = ReviewMapper.toReview(reviewRequestDTO);

        newreview.setMovie(movieService.findMovieById(movieId));

        reviewService.saveReview(newreview);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(newreview.getId())
                                .toUri())
                .body(reviewRequestDTO);
    }

    @GetMapping("/movies/{movieId}/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getReviews(@PathVariable long movieId) {
        Movie movie = movieService.findMovieById(movieId);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }

        List<ReviewResponseDTO> reviewResponseDTOS = new ArrayList<ReviewResponseDTO>();

        List<Review> reviews = movie.getReviews();

        for (Review review : reviews) {
            reviewResponseDTOS.add(ReviewMapper.toReviewResponseDTO(review));
        }

        return ResponseEntity.ok(reviewResponseDTOS);
    }
}
