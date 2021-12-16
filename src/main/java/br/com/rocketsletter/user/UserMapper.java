package br.com.rocketsletter.user;

import br.com.rocketsletter.email.Email;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO(user);
    }

    public User toUser(UserCreationDTO userDTO) {
        return new User(new Email(userDTO.getEmail()));
    }
}
