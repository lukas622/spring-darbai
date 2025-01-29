package lt.techin.resource.models;

public class Movie {
  private int id;
  private String title;
  private String director;

  public Movie(int id, String title, String director) {
    this.id = id;
    this.title = title;
    this.director = director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDirector() {
    return director;
  }
}
