package lt.techin.running.club.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  public Role(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Role() {
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
}
