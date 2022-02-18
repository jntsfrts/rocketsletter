package br.com.rocketsletter.service.exception;

import br.com.rocketsletter.model.User;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(User user) {
        super("The user (email address: " + user.getEmail() + ") already exists.");
    }

    public UserAlreadyExistsException() {
        super("The user already exists.");
    }
}
