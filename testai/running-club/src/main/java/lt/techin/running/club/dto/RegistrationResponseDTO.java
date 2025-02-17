package lt.techin.running.club.dto;

import java.util.Date;

public record RegistrationResponseDTO(long id, long userId, String eventName,
                                      Date registrationDate) {
}
