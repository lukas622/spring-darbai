package lt.techin.movie.review.dto.user;

import lt.techin.movie.review.model.User;

public class UserMapper {
    public static User toUser(UserRequestDTO dto) {
        return new User(dto.username(), dto.password(), dto.roles());
    }
}
