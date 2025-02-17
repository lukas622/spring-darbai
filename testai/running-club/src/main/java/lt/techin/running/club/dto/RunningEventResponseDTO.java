package lt.techin.running.club.dto;

import java.util.Date;

public record RunningEventResponseDTO(long id, String name, Date calendarDate, String location,
                                      int maxParticipants) {
}
