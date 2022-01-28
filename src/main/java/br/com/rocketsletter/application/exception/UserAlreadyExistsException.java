package br.com.rocketsletter.application.exception;

import br.com.rocketsletter.domain.model.User;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(User user) {
        super("The user (email address: " + user.getEmail().getAddress() + ") already exists.");
    }

    public UserAlreadyExistsException() {
        super("The user already exists.");
    }
}
