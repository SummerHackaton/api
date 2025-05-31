package org.example.summerhackaton.ui;


import org.example.summerhackaton.common.Constantes;
import org.example.summerhackaton.domain.model.Token;
import org.example.summerhackaton.domain.service.authentication.ServicioLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    private final ServicioLogin servicioLogin;

    public LoginController(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }

    @PostMapping( Constantes.PATH_LOGIN)
    public ResponseEntity<Token> login(@RequestParam String username,
                                       @RequestParam String password) {
        Token tokens = servicioLogin.login(username, password);
        return tokens == null
                ? ResponseEntity.status(401).body(null)
                : ResponseEntity.ok(tokens);
    }

    @GetMapping(Constantes.PATH_ACTIVATE + "{codigo}")
    public ResponseEntity<Boolean> activateAccount(@PathVariable String codigo) {
        servicioLogin.activate(codigo);
        return ResponseEntity.ok(true);
    }
}
