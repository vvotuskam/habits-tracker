package kz.team.habittracker.services.habitTracking;


import kz.team.habittracker.exceptions.HabitTrackingNotFoundException;
import kz.team.habittracker.models.HabitTracking;

import java.util.List;

public interface HabitTrackingService {

    HabitTracking saveHabitTracking(HabitTracking habitTracking);
    HabitTracking getHabitTrackingById(Long id) throws HabitTrackingNotFoundException;
    List<HabitTracking> getAllHabitTrackings();
    HabitTracking updateHabitTracking(HabitTracking habitTracking);
    void deleteHabitTracking(Long id);

}
