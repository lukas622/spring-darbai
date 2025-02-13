package lt.techin.car.rental.system.service;

import lt.techin.car.rental.system.model.Rental;
import lt.techin.car.rental.system.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
  private final RentalRepository rentalRepository;

  @Autowired
  public RentalService(RentalRepository rentalRepository) {
    this.rentalRepository = rentalRepository;
  }

  public List<Rental> getRentals() {
    return rentalRepository.findAll();
  }

  public Rental getRental(long id) {
    return rentalRepository.findById(id).orElseThrow(NullPointerException::new);
  }

  public Rental addRental(Rental rental) {
    return rentalRepository.save(rental);
  }

  public void deleteRental(long id) {
    rentalRepository.deleteById(id);
  }
}
