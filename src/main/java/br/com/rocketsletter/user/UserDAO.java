package br.com.rocketsletter.user;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface UserDAO {

    User saveUser(User user);
    ResponseEntity deleteUser(Integer id);
}
