package lt.techin.running.club.repository;

import lt.techin.running.club.model.RunningEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunningEventRepository extends JpaRepository<RunningEvent, Long> {


}
