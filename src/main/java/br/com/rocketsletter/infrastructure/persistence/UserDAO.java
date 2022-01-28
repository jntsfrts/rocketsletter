package br.com.rocketsletter.infrastructure.persistence;

import br.com.rocketsletter.domain.model.Email;
import br.com.rocketsletter.domain.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserDAO {

    User saveUser(User user);
    List<User> findAll();
    ResponseEntity deleteUser(Integer id);
    User findBy(Email email);
}
