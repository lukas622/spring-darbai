package lt.techin.running.club.controller;

import lt.techin.running.club.dto.UserRequestDTO;
import lt.techin.running.club.dto.UserResponseDTO;
import lt.techin.running.club.dto.mapper.UserMapper;
import lt.techin.running.club.model.Role;
import lt.techin.running.club.model.User;
import lt.techin.running.club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserResponseDTO>> getUsers() {
    return ResponseEntity.ok(UserMapper.toUserResponseDTOList(userService.findUsers()));
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<UserResponseDTO> getUser(@PathVariable long id) {
    return ResponseEntity.ok(UserMapper.toUserResponseDTO(userService.findUser(id)));
  }

  @PostMapping("/auth/register")
  public ResponseEntity<UserRequestDTO> addUser(@RequestBody User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(List.of(new Role("ROLE_USER")));

    User newUser = userService.addUser(user);

    UserRequestDTO userRequestDTO = UserMapper.toUserRequestDTO(newUser);

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(userRequestDTO.id())
                    .toUri())
            .body(userRequestDTO);
  }
}
