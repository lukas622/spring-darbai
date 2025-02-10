package lt.techin.sql_relations.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.sql_relations.model.Actor;
import lt.techin.sql_relations.model.Screening;
import lt.techin.sql_relations.validation.Title;

import java.util.List;

public record MovieDTO(long id, @NotNull
@Size(min = 2, max = 30)
@NotEmpty
@Title String title,  @NotNull
@Size(min = 2, max = 30)
@NotEmpty
@Pattern(regexp = "^[A-Z][a-z]+$", message = "Must start with uppercase letter, and continue as" +
        " lowercase") String director, List<Screening> screenings, List<Actor> actors) {
}
