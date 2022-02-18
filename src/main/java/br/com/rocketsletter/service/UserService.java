package br.com.rocketsletter.service;

import br.com.rocketsletter.model.User;
import br.com.rocketsletter.service.exception.InvalidEmailException;
import br.com.rocketsletter.service.exception.UserAlreadyExistsException;
import br.com.rocketsletter.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User saveUser(User user) {

        if(!isEmailValid(user.getEmail()))
            throw new InvalidEmailException(user.getEmail());


        if(userDAO.existsUserWith(user.getEmail()))
            throw new UserAlreadyExistsException();

        return userDAO.saveUser(user);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public ResponseEntity deleteUser(Integer id) {
        return userDAO.deleteUser(id);
    }

    private boolean isEmailValid(String emailAddress) {
        return emailAddress.matches("^([a-z0-9]+(?:[._-][a-z0-9]+)*)@([a-z0-9]+(?:[.-][a-z0-9]+)*\\.[a-z]{2,})$");
    }
}
