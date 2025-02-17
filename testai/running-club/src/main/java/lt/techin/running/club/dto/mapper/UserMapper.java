package lt.techin.running.club.dto.mapper;

import lt.techin.running.club.dto.UserRequestDTO;
import lt.techin.running.club.dto.UserResponseDTO;
import lt.techin.running.club.model.User;

import java.util.List;

public class UserMapper {
  public static List<UserResponseDTO> toUserResponseDTOList(List<User> users) {

    return users.stream().map((User user) -> new UserResponseDTO(user.getId(), user.getUsername(), user.getRoles())).toList();
  }

  public static UserRequestDTO toUserRequestDTO(User user) {
    return new UserRequestDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRegistrations(), user.getRoles());
  }

  public static UserResponseDTO toUserResponseDTO(User user) {
    return new UserResponseDTO(user.getId(), user.getUsername(), user.getRoles());
  }

  public static User UserRequestDTOToUser(UserRequestDTO userRequestDTO) {
    return new User(userRequestDTO.id(), userRequestDTO.username(), userRequestDTO.password(), userRequestDTO.registrations(), userRequestDTO.roles());
  }
}
