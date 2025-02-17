package lt.techin.sql_relations.controller;

import jakarta.validation.Valid;
import lt.techin.sql_relations.model.Movie;
import lt.techin.sql_relations.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {
  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping("/movies")
  public ResponseEntity<List<Movie>> getMovies() {
    return ResponseEntity.ok(movieService.findAllMovies());
  }

  @GetMapping("/movies/{id}")
  public ResponseEntity<Optional<Movie>> getMovie(@PathVariable long id) {
    if (!movieService.existsMovieById(id)) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(movieService.findMovieById(id));
  }

  @PostMapping("/movies")
  public ResponseEntity<?> saveMovie(@Valid @RequestBody Movie movie) {
    Movie savedMovie = movieService.saveMovie(movie);

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedMovie.getId())
                            .toUri())
            .body(savedMovie);
  }

  @PutMapping("/movies/{id}")
  public ResponseEntity<?> updateMovie(@Valid @PathVariable long id, @RequestBody Movie movie) {
    if (!movieService.existsMovieById(id)) {
      Movie newMovie = movieService.saveMovie(movie);
      return ResponseEntity.created(
                      ServletUriComponentsBuilder.fromCurrentRequest()
                              .path("/{id}")
                              .buildAndExpand(newMovie.getId())
                              .toUri())
              .body(movie);
    }

    return ResponseEntity.ok(movieService.updateMovie(id, movie));
  }

  @DeleteMapping("/movies/{id}")
  public ResponseEntity<Movie> deleteMovie(@PathVariable long id) {
    if (!movieService.existsMovieById(id)) return ResponseEntity.notFound().build();
    movieService.deleteMovieById(id);
    return ResponseEntity.ok().build();
  }
}
