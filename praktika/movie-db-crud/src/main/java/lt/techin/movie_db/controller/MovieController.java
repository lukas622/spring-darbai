package lt.techin.movie_db.controller;

import lt.techin.movie_db.model.Movie;
import lt.techin.movie_db.service.MovieService;
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
  public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
    // not sure how to do this with ResponseEntity.create
    return ResponseEntity.ok(movieService.saveMovie(movie));
  }

  @PutMapping("/movies/{id}")
  public ResponseEntity<Movie> updateMovie(@PathVariable long id, @RequestBody Movie movie) {
    if (!movieService.existsMovieById(id)) {
      // not sure how to do this with ResponseEntity.create
      return ResponseEntity.ok(movieService.saveMovie(movie));
    }

    if (movie.getDirector().isEmpty() || movie.getTitle().isEmpty() || movie.getDirector() == null || movie.getTitle() == null) {
      return ResponseEntity.badRequest().build();
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
