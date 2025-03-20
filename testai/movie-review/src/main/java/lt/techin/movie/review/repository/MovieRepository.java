package lt.techin.movie.review.repository;

import lt.techin.movie.review.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
