package lt.techin.sql_relations.controller;

import jakarta.validation.Valid;
import lt.techin.sql_relations.dto.MovieDTO;
import lt.techin.sql_relations.dto.MovieMapper;
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
  public ResponseEntity<List<MovieDTO>> getMovies() {
    return ResponseEntity.ok(MovieMapper.toMovieDTOList(movieService.findAllMovies()));
  }

  @GetMapping("/movies/{id}")
  public ResponseEntity<MovieDTO> getMovie(@PathVariable long id) {
    if (!movieService.existsMovieById(id)) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(MovieMapper.toMovieDTO(movieService.findMovieById(id)));
  }

  @PostMapping("/movies")
  public ResponseEntity<?> saveMovie(@Valid @RequestBody MovieDTO movieDTO) {
    Movie savedMovie = movieService.saveMovie(MovieMapper.toMovie(movieDTO));

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedMovie.getId())
                            .toUri())
            .body(MovieMapper.toMovieDTO(savedMovie));
  }

  @PutMapping("/movies/{id}")
  public ResponseEntity<?> updateMovie(@PathVariable long id, @Valid @RequestBody MovieDTO movieDTO) {
    if (!movieService.existsMovieById(id)) {
      Movie newMovie = movieService.saveMovie(MovieMapper.toMovie(movieDTO));
      return ResponseEntity.created(
                      ServletUriComponentsBuilder.fromCurrentRequest()
                              .path("/{id}")
                              .buildAndExpand(newMovie.getId())
                              .toUri())
              .body(MovieMapper.toMovieDTO(newMovie));
    }

    Movie movieFromDB = movieService.findMovieById(id);

    MovieMapper.updateMovieFromDTO(movieFromDB, movieDTO);

    return ResponseEntity.ok(movieService.updateMovie(id, movieFromDB));
  }

  @DeleteMapping("/movies/{id}")
  public ResponseEntity<Movie> deleteMovie(@PathVariable long id) {
    if (!movieService.existsMovieById(id)) return ResponseEntity.notFound().build();
    movieService.deleteMovieById(id);
    return ResponseEntity.ok().build();
  }
}
