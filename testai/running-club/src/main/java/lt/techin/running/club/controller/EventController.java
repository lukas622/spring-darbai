package lt.techin.running.club.controller;

import lt.techin.running.club.dto.*;
import lt.techin.running.club.dto.mapper.EventMapper;
import lt.techin.running.club.dto.mapper.RegistrationMapper;
import lt.techin.running.club.dto.mapper.UserMapper;
import lt.techin.running.club.model.Registration;
import lt.techin.running.club.model.Role;
import lt.techin.running.club.model.RunningEvent;
import lt.techin.running.club.model.User;
import lt.techin.running.club.service.EventService;
import lt.techin.running.club.service.RegistrationService;
import lt.techin.running.club.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

  private final EventService eventService;
  private final UserService userService;
  private final RegistrationService registrationService;

  @Autowired
  public EventController(EventService eventService, UserService userService, RegistrationService registrationService) {
    this.eventService = eventService;
    this.userService = userService;
    this.registrationService = registrationService;
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
  public ResponseEntity deleteEvent(@PathVariable long id) {
    if (!eventService.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    eventService.removeEvent(id);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/events/{id}/register")
  public ResponseEntity<RegistrationRequestDTO> register(@PathVariable long id) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    System.out.println(user.getUsername());

    Date newDate = new Date();
    Registration newRegistration = new Registration(user, eventService.findEvent(id), new Timestamp(newDate.getTime()));

    registrationService.addRegistration(newRegistration);

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newRegistration.getId())
                    .toUri())
            .body(RegistrationMapper.toRegistrationRequestDTO(newRegistration));
  }

  @GetMapping("/events/{id}/participants")
  public ResponseEntity<List<UserParticipantsDTO>> getEventParticipants(@PathVariable long id) {
    List<User> users = userService.findUsers();
    List<UserParticipantsDTO> participatingUsers = new ArrayList<UserParticipantsDTO>();

    for (User user : users) {
      for (Registration registration : user.getRegistrations()) {
        if (registration.getRunningEvent().getId() == id) {
          participatingUsers.add(new UserParticipantsDTO(user.getId(), user.getUsername()));
          break;
        }
      }
    }

    return ResponseEntity.ok(participatingUsers);
  }

}
