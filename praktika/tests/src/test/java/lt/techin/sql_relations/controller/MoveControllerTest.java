package lt.techin.sql_relations.controller;

import lt.techin.sql_relations.model.Movie;
import lt.techin.sql_relations.model.Screening;
import lt.techin.sql_relations.security.SecurityConfig;
import lt.techin.sql_relations.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MovieController.class)
@Import(SecurityConfig.class)
public class MoveControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    @Test
    @WithMockUser(authorities = "ADMIN")
    void getMovies_AuthenticatedUser() throws Exception {
        Movie movie1 = new Movie(1,"About Time", "Time Traveller");
        Movie movie2 = new Movie(2, "About Nature", "Nature Traveller");

        List<Movie> movies = List.of(movie1, movie2);

        given(movieService.findAllMovies()).willReturn(movies);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").exists())
                .andExpect(jsonPath("[0].title").value("About Time"))
                .andExpect(jsonPath("[0].director").value("Time Traveller"))
                .andExpect(jsonPath("[1].id").exists())
                .andExpect(jsonPath("[1].title").value("About Nature"))
                .andExpect(jsonPath("[1].director").value("Nature Traveller"));

        Mockito.verify(movieService, times(1)).findAllMovies();
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void getMovies_ZeroMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();

        given(movieService.findAllMovies()).willReturn(movies);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        Mockito.verify(movieService, times(1)).findAllMovies();
    }

    @Test
    void getMovies_UnauthenticatedUser() throws Exception {
        Movie movie1 = new Movie(1,"About Time", "Time Traveller");
        Movie movie2 = new Movie(2, "About Nature", "Nature Traveller");

        List<Movie> movies = List.of(movie1, movie2);

        given(movieService.findAllMovies()).willReturn(movies);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(""));


//        Mockito.verify(movieService, times(1)).findAllMovies();
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void getMovies_WithID() throws Exception {
        Movie movie1 = new Movie(1,"About Time", "Time Traveller");
        Movie movie2 = new Movie(2, "About Nature", "Nature Traveller");

        List<Movie> movies = List.of(movie1, movie2);

        given(movieService.findMovieById(1)).willReturn(Optional.of(movie1));

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/1"))
                .andExpect(status().isOk());

//        Mockito.verify(movieService, times(1)).findMovieById(1);
    }
}
