package br.com.rocketsletter.user;

import br.com.rocketsletter.email.Email;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(User user) {
        super("The user (email address: " + user.getEmail().getAddress() + ") already exists.");
    }

    public UserAlreadyExistsException() {
        super("The user already exists.");
    }
}
