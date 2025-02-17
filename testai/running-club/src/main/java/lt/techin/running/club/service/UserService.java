package lt.techin.running.club.service;

import lt.techin.running.club.model.User;
import lt.techin.running.club.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  public final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> findUsers() {
    return userRepository.findAll();
  }

  public User addUser(User user) {
    return userRepository.save(user);
  }

  public User findUser(long id) {
    return userRepository.findById(id).orElseThrow(NullPointerException::new);
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
