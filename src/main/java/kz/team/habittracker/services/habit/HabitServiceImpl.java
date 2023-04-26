package kz.team.habittracker.services.habit;

import kz.team.habittracker.exceptions.HabitNotFoundException;
import kz.team.habittracker.models.Habit;
import kz.team.habittracker.repositories.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService{

    private final HabitRepository habitRepository;

    @Override
    public Habit saveHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    @Override
    public Habit getHabit(Long id) throws HabitNotFoundException {
        return habitRepository.findById(id).orElseThrow(() -> new HabitNotFoundException("Habit with id " + id + " was not found"));
    }

    @Override
    public List<Habit> getHabits() {
        return habitRepository.findAll();
    }

    @Override
    public Habit updateHabit(Long id, Habit habit) {
        Habit existingHabit = habitRepository.findById(id).orElse(null);
        if (existingHabit == null) {
            return null;
        }
        existingHabit.setName(habit.getName());
        existingHabit.setDescription(habit.getDescription());
        existingHabit.setGoal(habit.getGoal());
        existingHabit.setFrequencyInterval(habit.getFrequencyInterval());
        existingHabit.setFrequencyType(habit.getFrequencyType());
        existingHabit.setStartDate(habit.getStartDate());
        existingHabit.setEndDate(habit.getEndDate());
        return habitRepository.save(existingHabit);
    }

    @Override
    public String deleteHabit(Long id) {
        habitRepository.deleteById(id);
        return "Habit#" + id + " deleted";
    }
}
