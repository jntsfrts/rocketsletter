package br.com.rocketsletter.application.exception;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException() {
        super("The email provided is not valid.");
    }
}
