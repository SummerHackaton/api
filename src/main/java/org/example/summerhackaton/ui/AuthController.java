package org.example.summerhackaton.ui;

import lombok.RequiredArgsConstructor;

import org.example.summerhackaton.common.Constantes;
import org.example.summerhackaton.domain.model.LoginResponse;
import org.example.summerhackaton.domain.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constantes.PATH_REFRESH_TOKEN)
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<LoginResponse> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(refreshToken, userDetails)) {
            String newToken = jwtService.generateToken(userDetails);
            String newRefreshToken = jwtService.generateRefreshToken(userDetails);
            LoginResponse loginResponse = new LoginResponse(newToken, newRefreshToken);
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }
}
