package lt.techin.car.rental.system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private int year;

  public Car(long id, String name, int year) {
    this.id = id;
    this.name = name;
    this.year = year;
  }

  public Car() {
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

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }
}
