package lt.techin.car.rental.system.dto;

import jakarta.validation.constraints.NotNull;

public record GetPostRequestRentalDTO(@NotNull long id, @NotNull long user_id, @NotNull long car_id,
                                      @NotNull double price, @NotNull boolean available) {
}
