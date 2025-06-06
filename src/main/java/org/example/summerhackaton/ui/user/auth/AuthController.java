package org.example.summerhackaton.ui.user.auth;

import lombok.RequiredArgsConstructor;
import org.example.summerhackaton.common.Constantes;
import org.example.summerhackaton.domain.model.Token;
import org.example.summerhackaton.domain.service.authentication.local.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping(Constantes.PATH_REFRESH_TOKEN)
    public ResponseEntity<Token> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(refreshToken, userDetails)) {
            String newToken = jwtService.generateToken(userDetails);
            String newRefreshToken = jwtService.generateRefreshToken(userDetails);
            Token token = new Token(newToken, newRefreshToken);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }
}
