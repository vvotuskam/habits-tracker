package kz.team.habittracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "habit_tracking")
@Data
public class HabitTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "habit_id")
    private Habit habit;

    @Column(name = "tracking_date")
    private LocalDate trackingDate;

    @Column(name = "measurement")
    private Integer measurement;

    @Column(name = "is_completed")
    private boolean completed;
}
