package br.com.rocketsletter.launch;

public class NoLaunchTodayException extends Exception {

    public NoLaunchTodayException() {
        super("No rocket launches found for today's date.");
    }
}
