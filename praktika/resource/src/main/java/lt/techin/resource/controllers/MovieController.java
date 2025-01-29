package lt.techin.resource.controllers;

import lt.techin.resource.models.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {
  List<Movie> movies = new ArrayList<>();

  @GetMapping("/movies")
  public ResponseEntity<List<Movie>> getMovies() {
    return ResponseEntity.ok(movies);
  }

  @GetMapping("/movies/{index}")
  public ResponseEntity<Movie> getMovie(@PathVariable int index) {
    if (index > (movies.size() - 1) || index < 0) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(movies.get(index));
  }

  @PostMapping("/movies")
  public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
    if (movie.getDirector().isEmpty() || movie.getTitle().isEmpty()) {
      return ResponseEntity.badRequest().build();
    }

    movies.add(movie);

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{index}").buildAndExpand(movies.size() - 1).toUri()).body(movie);
  }

}
