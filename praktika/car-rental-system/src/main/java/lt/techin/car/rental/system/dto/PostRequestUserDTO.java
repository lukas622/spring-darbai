package lt.techin.car.rental.system.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lt.techin.car.rental.system.model.Rental;
import lt.techin.car.rental.system.model.Role;

import java.util.List;

public record PostRequestUserDTO(@NotNull long id,
                                 @NotNull @NotEmpty @Size(min = 3, max = 16, message = "Username must be at least 3 characters long and 16 maximum") String username,
                                 @NotNull @NotEmpty String password,
                                 @NotNull
                                 List<Role> roles, @NotNull List<Rental> rentals) {


}
