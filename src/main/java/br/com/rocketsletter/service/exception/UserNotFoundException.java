package br.com.rocketsletter.service.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found.");
    }
}
