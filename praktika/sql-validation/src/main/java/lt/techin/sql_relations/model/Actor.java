package lt.techin.sql_relations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "actors")
public class Actor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @NotEmpty
  @Size(min = 2, max = 30)
  private String name;

  @NotNull
  @NotEmpty
  @Size(min = 2, max = 30)
  private String lastname;

  @NotNull
  @Min(0)
  @Max(120)
  private int age;

  @ManyToMany(mappedBy = "actors")
  private List<Movie> movies;

  public Actor(long id, String name, String lastname, int age) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.age = age;
  }

  public Actor() {

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

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

}
