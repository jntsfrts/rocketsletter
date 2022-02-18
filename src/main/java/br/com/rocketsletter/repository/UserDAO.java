package br.com.rocketsletter.repository;

import br.com.rocketsletter.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserDAO {

    User saveUser(User user);
    List<User> findAll();
    boolean existsUserWith(String email);
    ResponseEntity deleteUser(Integer id);
}
