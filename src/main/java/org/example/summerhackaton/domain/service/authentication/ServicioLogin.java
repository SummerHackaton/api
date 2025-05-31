package org.example.summerhackaton.domain.service.authentication;

import org.example.summerhackaton.common.PasswordHash;
import org.example.summerhackaton.dao.RolesRepository;
import org.example.summerhackaton.dao.UserRepository;
import org.example.summerhackaton.domain.model.Token;
import org.example.summerhackaton.domain.model.user.RolesEntity;
import org.example.summerhackaton.domain.model.user.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Service
public class ServicioLogin {
    private final PasswordHash encriptar;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private JwtService jwtService;
    private RolesRepository rolesRepository;

    public ServicioLogin(
            JwtService jwtService,
            PasswordHash encriptarSimetrico,
            UserDetailsService userDetailsService,
            UserRepository userRepository,
            RolesRepository rolesRepository) {
        this.jwtService = jwtService;
        this.encriptar = encriptarSimetrico;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    public Token login(String user, String pass) {
        boolean isAuthenticated = userRepository.findByUsername(user)
                .map(c -> {
                            try {
                                return encriptar.validatePassword(pass, c.getPassword())
                                        && c.isEnabled();
                            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).orElse(false);

        if (isAuthenticated) {
            String token = jwtService.generateToken(userDetailsService.loadUserByUsername(user));
            String refreshToken = jwtService.generateRefreshToken(userDetailsService.loadUserByUsername(user));
            return new Token(
                    token,
                    refreshToken
            );
        } else {
            return null;
        }
    }

    public boolean save(UserEntity userEntity) {
        byte[] salt = new byte[16];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(salt);

        userEntity.setCodigo(Base64.getUrlEncoder().encodeToString(salt));
        try {
            userEntity.setPassword(encriptar.createHash(userEntity.getPassword()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        rolesRepository.saveAll(userEntity.getRoles());

        UserEntity ret = userRepository.save(userEntity);
        return false;
    }

    public void activate(String codigo) {
        UserEntity user = userRepository.findByCodigo(codigo);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public RolesEntity getRolById(Long id){
        return rolesRepository.getRolesEntitiesById(id);
    }
}
