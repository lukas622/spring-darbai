package lt.techin.sql_relations.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lt.techin.sql_relations.model.Role;

import java.util.List;

public record GetUserRequestDTO(long id, @NotNull @NotEmpty String username,
                                @NotNull List<Role> roles) {
}
