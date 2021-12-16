package br.com.rocketsletter.email;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException() {
        super("The email provided is not valid.");
    }
}
