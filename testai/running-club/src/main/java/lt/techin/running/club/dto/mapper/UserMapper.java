package lt.techin.running.club.dto.mapper;

import lt.techin.running.club.dto.UserResponseDTO;
import lt.techin.running.club.model.User;

import java.util.List;

public class UserMapper {
  public static List<UserResponseDTO> toUserResponseDTOList(List<User> users) {

    return users.stream().map((User user) -> new UserResponseDTO(user.getId(), user.getUsername(), user.getRoles())).toList();
  }

}
