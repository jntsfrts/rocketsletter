package br.com.rocketsletter.user;

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
