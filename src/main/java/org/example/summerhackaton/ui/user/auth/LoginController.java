package org.example.summerhackaton.ui.user.auth;


import org.example.summerhackaton.common.Constantes;
import org.example.summerhackaton.domain.model.Token;
import org.example.summerhackaton.domain.model.requests.LoginRequest;
import org.example.summerhackaton.domain.model.user.RolesEntity;
import org.example.summerhackaton.domain.model.user.UserEntity;
import org.example.summerhackaton.domain.service.authentication.local.ServicioLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    private final ServicioLogin servicioLogin;

    public LoginController(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }

    @PostMapping( Constantes.PATH_LOGIN)
    public ResponseEntity<Token> login(@RequestBody LoginRequest loginRequest) {
        Token tokens = servicioLogin.login(loginRequest.getUsername(), loginRequest.getPassword());
        return tokens == null
                ? ResponseEntity.status(401).body(null)
                : ResponseEntity.ok(tokens);
    }

    @PostMapping(Constantes.PATH_REGISTER)
    public ResponseEntity<Void> signup(@RequestParam String username,
                                       @RequestParam String password) {
        final var userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setEnabled(true);
        userEntity.setRoles(Set.of(new RolesEntity(userEntity.getId(), "USER")));
        return
                servicioLogin.save(userEntity)
                        ? ResponseEntity.status(500).body(null)
                        : ResponseEntity.status(201).build();
    }
}
