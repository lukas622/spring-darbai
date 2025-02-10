package lt.techin.sql_relations.dto;

import lt.techin.sql_relations.model.Movie;
import lt.techin.sql_relations.model.User;

import java.util.List;

public class UserMapper {
  public static List<GetUserRequestDTO> toUserDTOList(List<User> users) {
    List<GetUserRequestDTO> result = users.stream()
            .map(user -> new GetUserRequestDTO(user.getId(), user.getUsername(), user.getRoles()))
            .toList();

    return result;
  }

  public static GetUserRequestDTO toUserDTO(User user) {
    return new GetUserRequestDTO(user.getId(), user.getUsername(), user.getRoles());
  }
  
  public static User AddUserRequestDTO_toUser(AddUserRequestDTO addUserRequestDTO) {
    User user = new User();
    user.setUsername(addUserRequestDTO.username());
    user.setPassword(addUserRequestDTO.password());
    user.setRoles(addUserRequestDTO.roles());

    return user;
  }

  public static User toUser(GetUserRequestDTO getUserRequestDTO) {
    User user = new User();
    user.setUsername(getUserRequestDTO.username());
    user.setRoles(getUserRequestDTO.roles());

    return user;
  }

  public static void updateMovieFromDTO(Movie movie, MovieDTO movieDTO) {
    movie.setTitle(movieDTO.title());
    movie.setDirector(movieDTO.director());
    movie.setScreenings(movieDTO.screenings());
    movie.setActors(movieDTO.actors());
  }

}
