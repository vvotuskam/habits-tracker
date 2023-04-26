package kz.team.habittracker.exceptions;

public class HabitNotFoundException extends Exception {

    public HabitNotFoundException() {
        super();
    }

    public HabitNotFoundException(String message) {
        super(message);
    }
}
