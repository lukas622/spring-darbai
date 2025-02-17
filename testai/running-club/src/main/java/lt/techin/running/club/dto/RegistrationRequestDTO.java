package lt.techin.running.club.dto;

import jakarta.validation.constraints.NotNull;
import lt.techin.running.club.model.User;

public record RegistrationRequestDTO(@NotNull User user) {
}
