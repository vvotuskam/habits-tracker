package kz.team.habittracker.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorMessenger {

    public static String createMessage(BindingResult result) {
        StringBuilder message = new StringBuilder();

        List<FieldError> errors = result.getFieldErrors();
        for (FieldError error : errors) {
            message
                    .append(error.getField())
                    .append(": ")
                    .append(error.getDefaultMessage())
                    .append(". ")
            ;
        }

        return message.toString().trim();
    }
}
