package lt.techin.running.club.repository;

import lt.techin.running.club.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
