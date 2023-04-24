package kz.team.habittracker.utils;

import kz.team.habittracker.auth.dto.RegistrationRequest;
import kz.team.habittracker.models.User;
import kz.team.habittracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegistrationValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(RegistrationRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationRequest request = (RegistrationRequest) target;

        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            errors.rejectValue("email", "", "User with email " + request.getEmail() +
                    " is already exists");
        }

    }
}
