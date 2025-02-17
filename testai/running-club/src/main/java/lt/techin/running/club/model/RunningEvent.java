package lt.techin.running.club.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "running_events")
public class RunningEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private Date calendarDate;

  private String location;

  private int maxParticipants;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private List<Registration> registrations;

  public RunningEvent(long id, String name, Date calendarDate, String location, int maxParticipants, List<Registration> registrations) {
    this.id = id;
    this.name = name;
    this.calendarDate = calendarDate;
    this.location = location;
    this.maxParticipants = maxParticipants;
    this.registrations = registrations;
  }

  public RunningEvent() {
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCalendarDate() {
    return calendarDate;
  }

  public void setCalendarDate(Date calendarDate) {
    this.calendarDate = calendarDate;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getMaxParticipants() {
    return maxParticipants;
  }

  public void setMaxParticipants(int maxParticipants) {
    this.maxParticipants = maxParticipants;
  }

  public List<Registration> getRegistrations() {
    return registrations;
  }

  public void setRegistrations(List<Registration> registrations) {
    this.registrations = registrations;
  }
}
