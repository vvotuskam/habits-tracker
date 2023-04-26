package kz.team.habittracker.services.habitTracking;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kz.team.habittracker.models.HabitTracking;
import kz.team.habittracker.repositories.HabitTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class HabitTrackingServiceImpl implements HabitTrackingService {

    private final HabitTrackingRepository habitTrackingRepository;

    @Autowired
    public HabitTrackingServiceImpl(HabitTrackingRepository habitTrackingRepository) {
        this.habitTrackingRepository = habitTrackingRepository;
    }

    public HabitTracking saveHabitTracking(HabitTracking habitTracking) {
        return habitTrackingRepository.save(habitTracking);
    }

    public HabitTracking getHabitTrackingById(Long id) {
        return habitTrackingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("HabitTracking not found with id " + id));
    }

    public List<HabitTracking> getAllHabitTrackings() {
        return habitTrackingRepository.findAll();
    }

    public HabitTracking updateHabitTracking(HabitTracking habitTracking) {
        HabitTracking existingHabitTracking = habitTrackingRepository.findById(habitTracking.getId())
                .orElseThrow(() -> new EntityNotFoundException("HabitTracking not found with id " + habitTracking.getId()));
        existingHabitTracking.setHabit(habitTracking.getHabit());
        existingHabitTracking.setTrackingDate(habitTracking.getTrackingDate());
        existingHabitTracking.setMeasurement(habitTracking.getMeasurement());
        existingHabitTracking.setCompleted(habitTracking.isCompleted());
        return habitTrackingRepository.save(existingHabitTracking);
    }

    public void deleteHabitTracking(Long id) {
        habitTrackingRepository.deleteById(id);
    }
}
