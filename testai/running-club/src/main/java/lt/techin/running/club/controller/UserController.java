package lt.techin.running.club.controller;

import lt.techin.running.club.dto.UserResponseDTO;
import lt.techin.running.club.dto.mapper.UserMapper;
import lt.techin.running.club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

  public final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserResponseDTO>> getUsers() {
    return ResponseEntity.ok(UserMapper.toUserResponseDTOList(userService.findUsers()));
  }

}
