package lt.techin.sql_relations.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.sql_relations.validation.Title;

import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;
  private String director;

  public List<Screening> getScreenings() {
    return screenings;
  }

  public void setScreenings(List<Screening> screenings) {
    this.screenings = screenings;
  }

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "movie_id")
  private List<Screening> screenings;

  @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(
          name = "movies_actors",
          joinColumns = @JoinColumn(name = "movie_id"),
          inverseJoinColumns = @JoinColumn(name = "actor_id")
  )
  private List<Actor> actors;

  public List<Actor> getActors() {
    return actors;
  }

  public void setActors(List<Actor> actors) {
    this.actors = actors;
  }

  public Movie(long id, String title, String director, List<Screening> screenings, List<Actor> actors) {
    this.id = id;
    this.title = title;
    this.director = director;
    this.screenings = screenings;
    this.actors = actors;
  }

  public Movie() {
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

}
