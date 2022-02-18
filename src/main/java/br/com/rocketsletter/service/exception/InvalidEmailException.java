package br.com.rocketsletter.service.exception;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String email) {
        super("The email "+ email +" provided is not valid.");
    }
}
