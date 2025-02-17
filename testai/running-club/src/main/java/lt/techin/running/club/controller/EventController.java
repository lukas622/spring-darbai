package lt.techin.running.club.controller;

import lt.techin.running.club.dto.RunningEventRequestDTO;
import lt.techin.running.club.dto.RunningEventResponseDTO;
import lt.techin.running.club.dto.UserRequestDTO;
import lt.techin.running.club.dto.UserResponseDTO;
import lt.techin.running.club.dto.mapper.EventMapper;
import lt.techin.running.club.dto.mapper.UserMapper;
import lt.techin.running.club.model.Role;
import lt.techin.running.club.model.RunningEvent;
import lt.techin.running.club.model.User;
import lt.techin.running.club.service.EventService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

  private final EventService eventService;

  @Autowired
  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @GetMapping("/events")
  public ResponseEntity<List<RunningEventResponseDTO>> getEvents() {
    return ResponseEntity.ok(EventMapper.toRunningEventResponseDTOList(eventService.findEvents()));
  }

  @GetMapping("/events/{id}")
  public ResponseEntity<RunningEventRequestDTO> getEvent(@PathVariable long id) {
    return ResponseEntity.ok(EventMapper.toRunningEventRequestDTO(eventService.findEvent(id)));
  }

  @PostMapping("/events")
  public ResponseEntity<RunningEventRequestDTO> addUser(@RequestBody RunningEvent event) {

    RunningEventRequestDTO runningEventRequestDTO = EventMapper.toRunningEventRequestDTO(eventService.addEvent(event));

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(runningEventRequestDTO.id())
                    .toUri())
            .body(runningEventRequestDTO);
  }

  @DeleteMapping("/events/{id}")
  public void deleteEvent(@PathVariable long id) {
    eventService.removeEvent(id);
  }

}
