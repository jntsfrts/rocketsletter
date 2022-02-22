package br.com.rocketsletter.repository;

import br.com.rocketsletter.model.User;

import java.util.List;

public interface UserDAO {

    User saveUser(User user);
    List<User> findAll();
    boolean existsUserWith(String email);
    Integer deleteUser(String id);
}
