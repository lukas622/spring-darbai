package lt.techin.running.club.dto;

import lt.techin.running.club.model.Role;

import java.util.List;

public record UserResponseDTO(long id, String username, List<Role> roles) {
}
