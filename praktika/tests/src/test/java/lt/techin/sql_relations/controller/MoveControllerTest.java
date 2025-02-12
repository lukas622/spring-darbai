package lt.techin.sql_relations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.techin.sql_relations.dto.MovieDTO;
import lt.techin.sql_relations.model.Actor;
import lt.techin.sql_relations.model.Movie;
import lt.techin.sql_relations.model.Screening;
import lt.techin.sql_relations.security.SecurityConfig;
import lt.techin.sql_relations.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        Movie movie1 = new Movie(1,"About Time", "Time Traveller", new ArrayList<Screening>(), new ArrayList<Actor>());
        Movie movie2 = new Movie(2, "About Nature", "Nature Traveller", new ArrayList<Screening>(), new ArrayList<Actor>());

        List<Movie> movies = List.of(movie1, movie2);

        given(movieService.findAllMovies()).willReturn(movies);

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").exists())
                .andExpect(jsonPath("[0].title").value("About Time"))
                .andExpect(jsonPath("[0].director").value("Time Traveller"))
                .andExpect(jsonPath("[0].screenings").isArray())
                .andExpect(jsonPath("[0].actors").isArray())
                .andExpect(jsonPath("[1].id").exists())
                .andExpect(jsonPath("[1].title").value("About Nature"))
                .andExpect(jsonPath("[1].director").value("Nature Traveller"))
                .andExpect(jsonPath("[1].screenings").isArray())
                .andExpect(jsonPath("[1].actors").isArray());

        Mockito.verify(movieService, times(1)).findAllMovies();
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void getMovies_ZeroMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();

        given(movieService.findAllMovies()).willReturn(movies);

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        Mockito.verify(movieService, times(1)).findAllMovies();
    }

    @Test
    void getMovies_UnauthenticatedUser() throws Exception {
        Movie movie1 = new Movie(1,"About Time", "Time Traveller", new ArrayList<Screening>(), new ArrayList<Actor>());
        Movie movie2 = new Movie(2, "About Nature", "Nature Traveller", new ArrayList<Screening>(), new ArrayList<Actor>());

        List<Movie> movies = List.of(movie1, movie2);

        given(movieService.findAllMovies()).willReturn(movies);

        mockMvc.perform(get("/movies"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(""));


//        Mockito.verify(movieService, times(1)).findAllMovies();
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void getMovies_WithID() throws Exception {
        Movie movie1 = new Movie(1,"About Time", "Time Traveller", new ArrayList<Screening>(), new ArrayList<Actor>());
        Movie movie2 = new Movie(2, "About Nature", "Nature Traveller", new ArrayList<Screening>(), new ArrayList<Actor>());

        given(movieService.existsMovieById(1)).willReturn(true);
        given(movieService.findMovieById(1)).willReturn(movie1);

        mockMvc.perform(get("/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("title").value("About Time"))
                .andExpect(jsonPath("director").value("Time Traveller"))
                .andExpect(jsonPath("screenings").isArray())
                .andExpect(jsonPath("actors").isArray());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void getMovies_InvalidID() throws Exception {
        Movie movie1 = new Movie(1,"About Time", "Time Traveller", new ArrayList<Screening>(), new ArrayList<Actor>());
        Movie movie2 = new Movie(2, "About Nature", "Nature Traveller", new ArrayList<Screening>(), new ArrayList<Actor>());

        given(movieService.existsMovieById(4)).willReturn(false);
        given(movieService.findMovieById(4)).willThrow(NullPointerException.class);

        mockMvc.perform(get("/movies/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }

    @Test
    void getMovies_Unauthenticated() throws Exception {
        Movie movie1 = new Movie(1,"About Time", "Time Traveller", new ArrayList<Screening>(), new ArrayList<Actor>());
        Movie movie2 = new Movie(2, "About Nature", "Nature Traveller", new ArrayList<Screening>(), new ArrayList<Actor>());

        given(movieService.existsMovieById(1)).willReturn(true);
        given(movieService.findMovieById(1)).willReturn(movie1);

        mockMvc.perform(get("/movies/1"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void addMovies_Admin() throws Exception {
        Movie movie1 = new Movie(1,"About Time", "Time Traveller", new ArrayList<Screening>(), new ArrayList<Actor>());

        given(movieService.saveMovie(ArgumentMatchers.any(Movie.class))).willReturn(movie1);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(movie1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("title").value("About Time"))
                .andExpect(jsonPath("director").value("Time Traveller"))
                .andExpect(jsonPath("screenings").isArray())
                .andExpect(jsonPath("actors").isArray());

        Mockito.verify(movieService, times(1)).saveMovie(ArgumentMatchers.any(Movie.class));
    }
}
