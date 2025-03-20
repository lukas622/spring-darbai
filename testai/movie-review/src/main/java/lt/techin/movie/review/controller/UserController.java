package lt.techin.movie.review.controller;

import jakarta.validation.Valid;
import lt.techin.movie.review.dto.user.UserMapper;
import lt.techin.movie.review.dto.user.UserRequestDTO;
import lt.techin.movie.review.model.Role;
import lt.techin.movie.review.model.User;
import lt.techin.movie.review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/auth/register")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        if (userService.findUserByUsername(userRequestDTO.username()).isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("username", "already exists");
            return ResponseEntity.badRequest().body(response);
        }

        User newUser = UserMapper.toUser(userRequestDTO);

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        // TODO: Make it so it doesn't create extra roles
        newUser.setRoles(List.of(new Role("ROLE_USER")));

        userService.saveUser(newUser);

        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(newUser.getId())
                                .toUri())
                .body(userRequestDTO);
    }
}
