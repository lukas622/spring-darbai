package lt.techin.car.rental.system.dto;

import lt.techin.car.rental.system.model.Rental;
import lt.techin.car.rental.system.model.User;

import java.util.List;

public class RentalMapper {
  public static List<GetPostRequestRentalDTO> getListToDTO(List<Rental> rentals) {
    return rentals.stream()
            .map(rental -> new GetPostRequestRentalDTO(rental.getId(), rental.getUser_id(), rental.getCar_id(), rental.getPrice(), rental.isAvailable()))
            .toList();
  }

  public static GetPostRequestRentalDTO getRentalToDTO(Rental rental) {
    return new GetPostRequestRentalDTO(rental.getId(), rental.getUser_id(), rental.getCar_id(), rental.getPrice(), rental.isAvailable());
  }
}
