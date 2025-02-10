package lt.techin.sql_relations.controller;

import jakarta.validation.Valid;
import lt.techin.sql_relations.dto.AddUserRequestDTO;
import lt.techin.sql_relations.dto.GetUserRequestDTO;
import lt.techin.sql_relations.dto.UserMapper;
import lt.techin.sql_relations.model.Role;
import lt.techin.sql_relations.model.User;
import lt.techin.sql_relations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class UserController {
  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/users")
  public ResponseEntity<List<GetUserRequestDTO>> getUsers() {
    return ResponseEntity.ok(UserMapper.toUserDTOList(userService.findAllUsers()));
  }

  @PostMapping("/users")
  public ResponseEntity<User> addUser(@Valid @RequestBody AddUserRequestDTO addUserRequestDTO) {
    User user = UserMapper.AddUserRequestDTO_toUser(addUserRequestDTO);

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(List.of(new Role("USER")));

    User savedUser = userService.saveUser(user);

    return ResponseEntity.created(
                    ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedUser.getId())
                            .toUri())
            .body(savedUser);
  }

}
