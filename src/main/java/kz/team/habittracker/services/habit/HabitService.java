package kz.team.habittracker.services.habit;

import kz.team.habittracker.exceptions.HabitNotFoundException;
import kz.team.habittracker.models.Habit;

import java.util.List;

public interface HabitService {

    Habit saveHabit(Habit habit);
    Habit getHabit(Long id) throws HabitNotFoundException;
    List<Habit> getHabits();
    Habit updateHabit(Long id, Habit habit);
    String deleteHabit(Long id);

}
