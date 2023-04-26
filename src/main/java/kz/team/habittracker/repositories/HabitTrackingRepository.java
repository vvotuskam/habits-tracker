package kz.team.habittracker.repositories;

import kz.team.habittracker.models.HabitTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HabitTrackingRepository extends JpaRepository<HabitTracking, Long> {
}
