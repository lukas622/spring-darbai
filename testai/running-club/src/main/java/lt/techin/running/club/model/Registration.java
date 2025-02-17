package lt.techin.running.club.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "registrations")
public class Registration {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  // ManyToOne
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;

  // ManyToOne
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "running_event_id")
  private RunningEvent runningEvent;

  public Registration(long id, User user, RunningEvent runningEvent, Timestamp registrationDate) {
    this.id = id;
    this.user = user;
    this.runningEvent = runningEvent;
    this.registrationDate = registrationDate;
  }

  private Timestamp registrationDate;

  public Registration() {
  }

  public long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public RunningEvent getRunningEvent() {
    return runningEvent;
  }

  public void setRunningEvent(RunningEvent runningEvent) {
    this.runningEvent = runningEvent;
  }

  public Timestamp getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Timestamp registrationDate) {
    this.registrationDate = registrationDate;
  }
}
