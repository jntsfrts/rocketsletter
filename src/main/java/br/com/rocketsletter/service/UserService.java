package br.com.rocketsletter.service;

import br.com.rocketsletter.model.User;
import br.com.rocketsletter.repository.UserDAO;
import br.com.rocketsletter.service.exception.InvalidEmailException;
import br.com.rocketsletter.service.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User saveUser(User user) {

        if(!isEmailValid(user.getEmail()))
            throw new InvalidEmailException(user.getEmail());

        if(userDAO.existsUserWith(user.getEmail()))
            throw new UserAlreadyExistsException();

        user.setCreatedAt(LocalDateTime.now());

        return userDAO.saveUser(user);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public Integer deleteUser(String id) {
        return userDAO.deleteUser(id);
    }

    private boolean isEmailValid(String emailAddress) {
        return emailAddress.matches("^([a-z0-9]+(?:[._-][a-z0-9]+)*)@([a-z0-9]+(?:[.-][a-z0-9]+)*\\.[a-z]{2,})$")
                && emailAddress != null
                && !emailAddress.isBlank();
    }
}
