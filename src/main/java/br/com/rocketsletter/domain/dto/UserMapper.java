package br.com.rocketsletter.domain.dto;

import br.com.rocketsletter.domain.model.Email;
import br.com.rocketsletter.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(user);
    }

    public User toUser(UserCreationDTO userDTO) {
        return new User(new Email(userDTO.getEmail()));
    }
}
