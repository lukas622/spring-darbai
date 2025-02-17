package lt.techin.running.club.repository;

import lt.techin.running.club.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {


}
