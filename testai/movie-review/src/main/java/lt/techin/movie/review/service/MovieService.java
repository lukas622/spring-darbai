package lt.techin.movie.review.service;

import lt.techin.movie.review.model.Movie;
import lt.techin.movie.review.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

    public Movie findMovieById(long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
