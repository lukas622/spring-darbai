package lt.techin.movie.review.controller;

import jakarta.validation.Valid;
import lt.techin.movie.review.dto.movie.MovieMapper;
import lt.techin.movie.review.dto.movie.MovieRequestDTO;
import lt.techin.movie.review.dto.user.UserMapper;
import lt.techin.movie.review.dto.user.UserRequestDTO;
import lt.techin.movie.review.model.Movie;
import lt.techin.movie.review.model.Role;
import lt.techin.movie.review.model.User;
import lt.techin.movie.review.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MovieController {
    public final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movies")
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieRequestDTO movieRequestDTO) {

        Movie newMovie = MovieMapper.toMovie(movieRequestDTO);

        movieService.saveMovie(newMovie);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(newMovie.getId())
                                .toUri())
                .body(movieRequestDTO);
    }

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable long movieId) {
        if (movieService.findMovieById(movieId) == null) {
            return ResponseEntity.notFound().build();
        }
        movieService.deleteMovie(movieService.findMovieById(movieId));
        return ResponseEntity.ok().build();
    }
}
