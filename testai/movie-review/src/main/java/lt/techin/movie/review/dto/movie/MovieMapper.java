package lt.techin.movie.review.dto.movie;

import lt.techin.movie.review.model.Movie;

public class MovieMapper {
    public static Movie toMovie(MovieRequestDTO dto) {
        return new Movie(dto.genre(), dto.releaseYear(), dto.title());
    }
}
