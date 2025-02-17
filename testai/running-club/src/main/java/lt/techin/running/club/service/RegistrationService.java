package lt.techin.running.club.service;

import lt.techin.running.club.model.Registration;
import lt.techin.running.club.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
  public final RegistrationRepository registrationRepository;

  @Autowired
  public RegistrationService(RegistrationRepository registrationRepository) {
    this.registrationRepository = registrationRepository;
  }

  public void addRegistration(Registration registration) {
    registrationRepository.save(registration);
  }

  public List<Registration> getRegistrations() {
    return registrationRepository.findAll();
  }
}
