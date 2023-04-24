package kz.team.habittracker.auth;

import kz.team.habittracker.auth.dto.AuthenticationRequest;
import kz.team.habittracker.auth.dto.AuthenticationResponse;
import kz.team.habittracker.auth.dto.RegistrationRequest;
import kz.team.habittracker.models.User;

public interface AuthenticationService {
    AuthenticationResponse register(RegistrationRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void saveUserToken(User user, String token);
    void revokeAllUserTokens(User user);

    String confirmToken(String token);
}
