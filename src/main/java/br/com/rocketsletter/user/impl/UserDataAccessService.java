package br.com.rocketsletter.user.impl;

import br.com.rocketsletter.user.User;
import br.com.rocketsletter.user.UserDAO;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataAccessService implements UserDAO {

    @Override
    public User saveUser(User user) {
        throw new RuntimeException("Not implemented!");
    }
}
