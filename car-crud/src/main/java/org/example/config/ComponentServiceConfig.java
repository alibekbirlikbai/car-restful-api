package org.example.config;

import org.example.controller.AuthController;
import org.example.controller.CarController;
import org.example.repository.CarRepository;
import org.example.repository.UserInfoRepository;
import org.example.service.CarService;
import org.example.service.impl.CarServiceImpl;
import org.example.service.impl.JwtService;
import org.example.service.impl.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ComponentServiceConfig {
    private final CarRepository carRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public ComponentServiceConfig(CarRepository carRepository,
                                  UserInfoRepository userInfoRepository,
                                  PasswordEncoder encoder,
                                  JwtService jwtService
    ) {
        this.carRepository = carRepository;
        this.userInfoRepository = userInfoRepository;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    @Bean
    public CarService carService() {
        return new CarServiceImpl(carRepository);
    }

    @Bean
    public CarController carController() {
        return new CarController(carService());
    }

    @Bean
    public UserInfoService userInfoService() {
        return new UserInfoService(userInfoRepository, encoder, jwtService);
    }

    @Bean
    public AuthController authController() {
        return new AuthController(userInfoService());
    }
}
