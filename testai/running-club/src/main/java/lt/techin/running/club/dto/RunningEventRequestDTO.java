package lt.techin.running.club.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record RunningEventRequestDTO(@NotNull long id, @NotNull @Size(min = 5) String name,
                                     @NotNull @Future Date calendarDate,
                                     @NotNull @Pattern(regexp = "^[^A-Za-z0-9]+$") String location,
                                     @Size(min = 1) int maxParticipants) {
}
