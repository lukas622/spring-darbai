package lt.techin.sql_relations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "screenings")
public class Screening {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Size(min = 2, max = 30)
  @NotEmpty
  private String theater;

  @NotNull
  private Date startTime;

  @NotNull
  private Date endTime;

  @NotNull
  private int availableSeats;

  @NotNull
  private BigDecimal price;

  public long getId() {
    return id;
  }

  public String getTheater() {
    return theater;
  }

  public void setTheater(String theater) {
    this.theater = theater;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public int getAvailableSeats() {
    return availableSeats;
  }

  public void setAvailableSeats(int availableSeats) {
    this.availableSeats = availableSeats;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Screening(long id, String theater, Date startTime, Date endTime, int availableSeats, BigDecimal price) {
    this.id = id;
    this.theater = theater;
    this.startTime = startTime;
    this.endTime = endTime;
    this.availableSeats = availableSeats;
    this.price = price;
  }

  public Screening() {
  }
}
