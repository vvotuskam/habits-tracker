package kz.team.habittracker.utils;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ErrorResponse {
    private String message;
    private Timestamp timestamp;
    private int status;
}
