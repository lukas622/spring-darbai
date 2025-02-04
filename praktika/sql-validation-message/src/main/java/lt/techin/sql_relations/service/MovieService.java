package lt.techin.sql_relations.service;

import lt.techin.sql_relations.model.Movie;
import lt.techin.sql_relations.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
  private final MovieRepository movieRepository;

  @Autowired
  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public List<Movie> findAllMovies() {
    return movieRepository.findAll();
  }

  public Optional<Movie> findMovieById(long id) {
    return movieRepository.findById(id);
  }

  public Movie saveMovie(Movie movie) {
    return movieRepository.save(movie);
  }

  public boolean existsMovieById(long id) {
    return movieRepository.existsById(id);
  }

  public Movie updateMovie(long id, Movie movie) {
    Movie foundmovie = movieRepository.findById(id).orElseThrow(RuntimeException::new);

    foundmovie.setId(movie.getId());
    foundmovie.setDirector(movie.getDirector());
    foundmovie.setTitle(movie.getTitle());
    foundmovie.setScreenings(movie.getScreenings());

    return movieRepository.save(foundmovie);
  }

  public void deleteMovieById(long id) {
    movieRepository.deleteById(id);
  }
}
