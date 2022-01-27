package br.com.rocketsletter.user;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserDAO {

    User saveUser(User user);
    List<User> findAll();
    ResponseEntity deleteUser(Integer id);
    User findBy(Email email);
}
