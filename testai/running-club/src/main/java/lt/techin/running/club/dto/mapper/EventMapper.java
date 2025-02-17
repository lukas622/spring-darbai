package lt.techin.running.club.dto.mapper;

import lt.techin.running.club.dto.RunningEventRequestDTO;
import lt.techin.running.club.dto.RunningEventResponseDTO;
import lt.techin.running.club.dto.UserRequestDTO;
import lt.techin.running.club.dto.UserResponseDTO;
import lt.techin.running.club.model.RunningEvent;
import lt.techin.running.club.model.User;

import java.util.List;

public class EventMapper {
  public static List<RunningEventResponseDTO> toRunningEventResponseDTOList(List<RunningEvent> events) {

    return events.stream().map((RunningEvent event) -> new RunningEventResponseDTO(event.getId(), event.getName(), event.getCalendarDate(), event.getLocation(), event.getMaxParticipants())).toList();
  }

  public static RunningEventRequestDTO toRunningEventRequestDTO(RunningEvent event) {
    return new RunningEventRequestDTO(event.getId(), event.getName(), event.getCalendarDate(), event.getLocation(), event.getMaxParticipants());
  }

}
