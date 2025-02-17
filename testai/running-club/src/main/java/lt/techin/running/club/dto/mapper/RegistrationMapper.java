package lt.techin.running.club.dto.mapper;

import lt.techin.running.club.dto.RegistrationRequestDTO;
import lt.techin.running.club.dto.UserRequestDTO;
import lt.techin.running.club.model.Registration;
import lt.techin.running.club.model.User;

public class RegistrationMapper {

  public static RegistrationRequestDTO toRegistrationRequestDTO(Registration registration) {
    return new RegistrationRequestDTO(registration.getUser());
  }

}
