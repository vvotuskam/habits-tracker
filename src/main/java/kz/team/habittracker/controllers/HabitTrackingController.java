package kz.team.habittracker.controllers;

import kz.team.habittracker.models.HabitTracking;
import kz.team.habittracker.services.habitTracking.HabitTrackingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/habit-trackings")
public class HabitTrackingController {

    private final HabitTrackingServiceImpl habitTrackingService;

    @Autowired
    public HabitTrackingController(HabitTrackingServiceImpl habitTrackingService) {
        this.habitTrackingService = habitTrackingService;
    }

    @PostMapping
    public ResponseEntity<HabitTracking> createHabitTracking(@RequestBody HabitTracking habitTracking) {
        HabitTracking createdHabitTracking = habitTrackingService.saveHabitTracking(habitTracking);
        return ResponseEntity.created(URI.create("/api/habit-trackings/" + createdHabitTracking.getId()))
                .body(createdHabitTracking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitTracking> getHabitTrackingById(@PathVariable Long id) {
        HabitTracking habitTracking = habitTrackingService.getHabitTrackingById(id);
        return ResponseEntity.ok(habitTracking);
    }

    @GetMapping
    public ResponseEntity<List<HabitTracking>> getAllHabitTrackings() {
        List<HabitTracking> habitTrackings = habitTrackingService.getAllHabitTrackings();
        return ResponseEntity.ok(habitTrackings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitTracking> updateHabitTracking(@PathVariable Long id, @RequestBody HabitTracking habitTracking) {
        habitTracking.setId(id);
        HabitTracking updatedHabitTracking = habitTrackingService.updateHabitTracking(habitTracking);
        return ResponseEntity.ok(updatedHabitTracking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitTracking(@PathVariable Long id) {
        habitTrackingService.deleteHabitTracking(id);
        return ResponseEntity.noContent().build();
    }
}
