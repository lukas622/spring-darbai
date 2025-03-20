package lt.techin.movie.review.repository;

import lt.techin.movie.review.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
