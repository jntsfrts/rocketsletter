package br.com.rocketsletter.user;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException() {
        super("The email provided is not valid.");
    }
}
