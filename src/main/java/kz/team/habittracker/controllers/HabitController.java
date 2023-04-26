package kz.team.habittracker.controllers;


import kz.team.habittracker.exceptions.HabitNotFoundException;
import kz.team.habittracker.models.Habit;
import kz.team.habittracker.services.habit.HabitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/habits")
public class HabitController {

    @Autowired
    private HabitServiceImpl habitService;

    @GetMapping
    public List<Habit> getAllHabits() {
        return habitService.getHabits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Long id) throws HabitNotFoundException {
        Habit habit = habitService.getHabit(id);
        if (habit == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(habit);
    }

    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody Habit habit) {
        Habit createdHabit = habitService.saveHabit(habit);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHabit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @RequestBody Habit habit) {
        Habit updatedHabit = habitService.updateHabit(id, habit);
        if (updatedHabit == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedHabit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }

}