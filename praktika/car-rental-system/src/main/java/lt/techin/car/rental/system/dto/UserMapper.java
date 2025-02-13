package lt.techin.car.rental.system.dto;

import lt.techin.car.rental.system.model.User;

import java.util.List;

public class UserMapper {
  public static List<GetRequestUserDTO> getListToDTO(List<User> users) {
    return users.stream()
            .map(user -> new GetRequestUserDTO(user.getId(), user.getUsername(), user.getRoles(), user.getRentals()))
            .toList();
  }

  public static GetRequestUserDTO getUserToDTO(User user) {
    return new GetRequestUserDTO(user.getId(), user.getUsername(), user.getRoles(), user.getRentals());
  }

  public static PostRequestUserDTO postUserToDTO(User user) {
    return new PostRequestUserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRoles(), user.getRentals());
  }
}
