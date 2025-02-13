package lt.techin.car.rental.system.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lt.techin.car.rental.system.model.Rental;
import lt.techin.car.rental.system.model.Role;

import java.util.List;

// We don't want any sensitive data here
public record GetRequestUserDTO(@NotNull long id, @NotNull @NotEmpty String username, @NotNull
List<Role> roles, @NotNull List<Rental> rentals) {
}
