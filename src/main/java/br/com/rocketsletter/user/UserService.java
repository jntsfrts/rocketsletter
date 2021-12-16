package br.com.rocketsletter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User saveUser(User user) {
        return userDAO.saveUser(user);
    }
}
