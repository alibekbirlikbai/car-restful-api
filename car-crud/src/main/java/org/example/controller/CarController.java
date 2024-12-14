package org.example.controller;

import jakarta.validation.Valid;
import org.example.exception.CarNotFoundException;
import org.example.model.Car;
import org.example.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/car-crud/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping
    public List<Car> getCars() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable UUID id) throws CarNotFoundException {
        return new ResponseEntity<>(carService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public String saveCar(@Valid @RequestBody Car car) {
        carService.save(car);
        return "successfully saved";
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable UUID id) throws CarNotFoundException {
        carService.deleteById(id);
        return "successfully deleted";
    }

    @PutMapping("/{id}")
    public String updateCar(@PathVariable UUID id, @Valid @RequestBody Car car) throws CarNotFoundException {
        carService.update(id, car);
        return "successfully updated";
    }
}
