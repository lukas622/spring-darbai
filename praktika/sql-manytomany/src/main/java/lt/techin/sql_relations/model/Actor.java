package lt.techin.sql_relations.model;

import jakarta.persistence.*;

@Entity
@Table(name = "actors")
public class Actor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private String lastname;

  public Actor(long id, String name, String lastname, int age) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.age = age;
  }

  public Actor() {
  }

  private int age;

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
