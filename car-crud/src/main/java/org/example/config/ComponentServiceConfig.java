package org.example.config;

import org.example.controller.CarController;
import org.example.repository.CarRepository;
import org.example.service.CarService;
import org.example.service.impl.CarServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentServiceConfig {
    private final CarRepository carRepository;

    public ComponentServiceConfig(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Bean
    public CarService carService() {
        return new CarServiceImpl(carRepository);
    }

    @Bean
    public CarController carController() {
        return new CarController(carService());
    }
}
