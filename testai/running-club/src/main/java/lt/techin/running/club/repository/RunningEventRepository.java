package lt.techin.running.club.repository;

import lt.techin.running.club.model.RunningEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunningEventRepository extends JpaRepository<RunningEvent, Long> {


}
