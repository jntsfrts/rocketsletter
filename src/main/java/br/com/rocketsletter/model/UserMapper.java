package br.com.rocketsletter.model;

import br.com.rocketsletter.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public static UserResponseDTO toDto(User user) {
        return new UserResponseDTO(user.getId().toString(), user.getEmail(), user.getCreatedAt());
    }

    public static User toUser(UserResponseDTO dto) {
        return new User(UUID.fromString(dto.getId()), dto.getEmail(), dto.getCreatedAt());
    }
}
