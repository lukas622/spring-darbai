package lt.techin.sql_relations.dto;

import lt.techin.sql_relations.model.Movie;
import lt.techin.sql_relations.model.User;

import java.util.List;

public class UserMapper {
  public static List<UserDTO> toUserDTOList(List<User> users) {
    List<UserDTO> result = users.stream()
            .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getRoles()))
            .toList();

    return result;
  }

  public static UserDTO toUserDTO(User user) {
    return new UserDTO(user.getId(), user.getUsername(), user.getRoles());
  }

  public static User toUser(UserDTO userDTO) {
    User user = new User();
    user.setUsername(userDTO.username());
    user.setRoles(userDTO.roles());

    return user;
  }

  public static void updateMovieFromDTO(Movie movie, MovieDTO movieDTO) {
    movie.setTitle(movieDTO.title());
    movie.setDirector(movieDTO.director());
    movie.setScreenings(movieDTO.screenings());
    movie.setActors(movieDTO.actors());
  }

}
