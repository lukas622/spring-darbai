package lt.techin.running.club.service;

import lt.techin.running.club.model.RunningEvent;
import lt.techin.running.club.model.User;
import lt.techin.running.club.repository.RunningEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
  public final RunningEventRepository runningEventRepository;

  @Autowired
  public EventService(RunningEventRepository runningEventRepository) {
    this.runningEventRepository = runningEventRepository;
  }

  public List<RunningEvent> findEvents() {
    return runningEventRepository.findAll();
  }

  public RunningEvent findEvent(long id) {
    return runningEventRepository.findById(id).orElseThrow(NullPointerException::new);
  }

  public RunningEvent addEvent(RunningEvent event) {
    return runningEventRepository.save(event);
  }

  public void removeEvent(long id) {
    runningEventRepository.deleteById(id);
  }
}
