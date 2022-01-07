package br.com.rocketsletter.user;

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

        if(user.getCreatedAt() == null)
            user.setCreatedAt(LocalDateTime.now());

        return userDAO.saveUser(user);
    }

    public List<User> findAll() {

        return userDAO.findAll();
    }

    public ResponseEntity deleteUser(Integer id) {
        return userDAO.deleteUser(id);
    }
}
