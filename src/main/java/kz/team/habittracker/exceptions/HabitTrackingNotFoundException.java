package kz.team.habittracker.exceptions;

public class HabitTrackingNotFoundException extends Exception {
    public HabitTrackingNotFoundException() {
        super();
    }

    public HabitTrackingNotFoundException(String message) {
        super(message);
    }
}
