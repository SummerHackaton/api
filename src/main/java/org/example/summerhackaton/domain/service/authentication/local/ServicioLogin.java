package org.example.summerhackaton.domain.service.authentication.local;

import org.example.summerhackaton.common.PasswordHash;
import org.example.summerhackaton.dao.RolesRepository;
import org.example.summerhackaton.dao.UserRepository;
import org.example.summerhackaton.domain.model.Token;
import org.example.summerhackaton.domain.model.apisModel.ApplicationServer;
import org.example.summerhackaton.domain.model.apisModel.Device;
import org.example.summerhackaton.domain.model.device_location_api.petition.DeviceLocationPetition;
import org.example.summerhackaton.domain.model.device_location_api.petition.UEID;
import org.example.summerhackaton.domain.model.quality_on_demand_api.QualityOnDemandSessionRequest;
import org.example.summerhackaton.domain.model.user.UserEntity;
import org.example.summerhackaton.domain.service.apis.QualityOnDemandService;
import org.example.summerhackaton.ui.apis.DeviceLocationVerificationController;
import org.example.summerhackaton.ui.apis.QualityOnDemandController;
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
    private final JwtService jwtService;
    private final RolesRepository rolesRepository;

    private final QualityOnDemandController api1;
    private final DeviceLocationVerificationController api2;

    public ServicioLogin(
            JwtService jwtService,
            PasswordHash encriptarSimetrico,
            UserDetailsService userDetailsService,
            UserRepository userRepository,
            RolesRepository rolesRepository, QualityOnDemandController api1, DeviceLocationVerificationController api2) {
        this.jwtService = jwtService;
        this.encriptar = encriptarSimetrico;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.api1 = api1;
        this.api2 = api2;
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

            // Llamada a QualityOnDemandService
            QualityOnDemandSessionRequest sessionRequest = new QualityOnDemandSessionRequest();
            sessionRequest.setDevice(new org.example.summerhackaton.domain.model.quality_on_demand_api.QualityOnDemandSessionRequest.Device("+34649379033"));
            sessionRequest.setApplicationServer(new org.example.summerhackaton.domain.model.quality_on_demand_api.QualityOnDemandSessionRequest.ApplicationServer("10.19.249.233/16"));
            sessionRequest.setQosProfile("QOS_L");
            sessionRequest.setDuration(86400);
            api1.createSession(sessionRequest);

            DeviceLocationPetition petition = new DeviceLocationPetition();
            petition.setUeid(new UEID("+34649379033"));
            petition.setLatitude(40.41682);
            petition.setLongitude(-3.68473);
            petition.setAccuracy(2);
            api2.isOnFestivalRange(petition);

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


    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

}
