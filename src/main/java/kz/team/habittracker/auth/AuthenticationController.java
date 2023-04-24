package kz.team.habittracker.auth;

import jakarta.validation.Valid;
import kz.team.habittracker.auth.dto.AuthenticationRequest;
import kz.team.habittracker.auth.dto.AuthenticationResponse;
import kz.team.habittracker.auth.dto.RegistrationRequest;
import kz.team.habittracker.exceptions.AuthenticationException;
import kz.team.habittracker.exceptions.RegisterException;
import kz.team.habittracker.utils.ErrorMessenger;
import kz.team.habittracker.utils.ErrorResponse;
import kz.team.habittracker.utils.RegistrationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RegistrationValidator registrationValidator;

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegistrationRequest request,
                                                           BindingResult result) throws RegisterException {

        registrationValidator.validate(request, result);

        if (result.hasErrors()) {
            String message = ErrorMessenger.createMessage(result);
            throw new RegisterException(message);
        }
        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.CREATED);
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return new ResponseEntity<>(authenticationService.confirmToken(token), HttpStatus.ACCEPTED);
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request,
                                                               BindingResult result) throws AuthenticationException {

        if (result.hasErrors()) {
            String message = ErrorMessenger.createMessage(result);
            throw new AuthenticationException(message);
        }

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RegisterException e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(AuthenticationException e) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
