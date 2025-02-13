package lt.techin.car.rental.system.service;

import lt.techin.car.rental.system.model.User;
import lt.techin.car.rental.system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(long id) {
    return userRepository.findById(id).orElseThrow(NullPointerException::new);
  }

  public User addUser(User user) {
    return userRepository.save(user);
  }

  public boolean userExistsById(long id) {
    return userRepository.existsById(id);
  }

  public void deleteUser(long id) {
    userRepository.deleteById(id);
  }
}
