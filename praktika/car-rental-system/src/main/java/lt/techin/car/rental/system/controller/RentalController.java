package lt.techin.car.rental.system.controller;

import lt.techin.car.rental.system.dto.GetPostRequestRentalDTO;
import lt.techin.car.rental.system.dto.RentalMapper;
import lt.techin.car.rental.system.model.Rental;
import lt.techin.car.rental.system.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalController {

  private final RentalService rentalService;

  @Autowired
  public RentalController(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  @GetMapping("/rentals")
  public ResponseEntity<List<GetPostRequestRentalDTO>> getRentals() {
    return ResponseEntity.ok(RentalMapper.getListToDTO(rentalService.getRentals()));
  }

  @GetMapping("/rentals/{id}")
  public ResponseEntity<GetPostRequestRentalDTO> getRental(@RequestParam long id) {
    return ResponseEntity.ok(RentalMapper.getRentalToDTO(rentalService.getRental(id)));
  }

  @PostMapping("/rentals")
  public ResponseEntity<GetPostRequestRentalDTO> addRental(@RequestBody Rental rental) {

    GetPostRequestRentalDTO getPostRequestRentalDTO = RentalMapper.getRentalToDTO(rental);

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(getPostRequestRentalDTO.id())
                    .toUri())
            .body(getPostRequestRentalDTO);
  }

  // TODO: Add PUT mapping

  @DeleteMapping("/rentals/{id}")
  public void deleteRental(@RequestParam long id) {
    rentalService.deleteRental(id);
  }
}
