package br.com.rocketsletter.service;

import br.com.rocketsletter.model.User;
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

        User result = userDAO.findBy(user.getEmail());

        if(result.getId() != null) {
            throw new UserAlreadyExistsException();
        }

        if(user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }

        return userDAO.saveUser(user);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public ResponseEntity deleteUser(Integer id) {
        return userDAO.deleteUser(id);
    }
}
