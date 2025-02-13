package lt.techin.car.rental.system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rentals")
public class Rental {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long user_id;

  private long car_id;

  private double price;

  private boolean available;

  public Rental(long id, long user_id, long car_id, double price, boolean available) {
    this.id = id;
    this.user_id = user_id;
    this.car_id = car_id;
    this.price = price;
    this.available = available;
  }

  public Rental() {
  }

  public long getId() {
    return id;
  }

  public long getUser_id() {
    return user_id;
  }

  public void setUser_id(long user_id) {
    this.user_id = user_id;
  }

  public long getCar_id() {
    return car_id;
  }

  public void setCar_id(long car_id) {
    this.car_id = car_id;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }
}
