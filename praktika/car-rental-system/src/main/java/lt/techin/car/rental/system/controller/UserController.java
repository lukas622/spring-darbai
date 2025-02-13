package lt.techin.car.rental.system.controller;

import lt.techin.car.rental.system.dto.GetRequestUserDTO;
import lt.techin.car.rental.system.dto.PostRequestUserDTO;
import lt.techin.car.rental.system.dto.UserMapper;
import lt.techin.car.rental.system.model.Role;
import lt.techin.car.rental.system.model.User;
import lt.techin.car.rental.system.service.UserService;
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
  public ResponseEntity<List<GetRequestUserDTO>> getUsers() {
    return ResponseEntity.ok(UserMapper.getListToDTO(userService.getAllUsers()));
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<GetRequestUserDTO> getUser(@RequestParam long id) {
    return ResponseEntity.ok(UserMapper.getUserToDTO(userService.getUserById(id)));
  }

  @PostMapping("/users")
  public ResponseEntity<PostRequestUserDTO> addUser(@RequestBody User user) {

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(List.of(new Role("USER")));

    User newUser = userService.addUser(user);

    PostRequestUserDTO postUserToDTO = UserMapper.postUserToDTO(newUser);

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(postUserToDTO.id())
                    .toUri())
            .body(postUserToDTO);
  }

  // TODO: Add PUT mapping

  @DeleteMapping("/users/{id}")
  public void deleteUser(@RequestParam long id) {
    userService.deleteUser(id);
  }
}
