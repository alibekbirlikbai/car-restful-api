package org.example.service.impl;

import org.example.exception.CarNotFoundException;
import org.example.model.Car;
import org.example.repository.CarRepository;
import org.example.service.CarService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(UUID id) throws CarNotFoundException {
        Optional<Car> car = carRepository.findById(id);

        if (car.isEmpty()) {
            throw new CarNotFoundException("Car with id " + id + " not found");
        } else {
            return car.get();
        }
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteById(UUID id) throws CarNotFoundException {
        Optional<Car> car = carRepository.findById(id);

        if (car.isEmpty()) {
            throw new CarNotFoundException("Car with id " + id + " not found");
        } else {
            carRepository.deleteById(id);
        }
    }

    @Override
    public Car update(UUID id, Car new_car) throws CarNotFoundException {
        Optional<Car> old_car = carRepository.findById(id);

        if (old_car.isEmpty()) {
            throw new CarNotFoundException("Car with id " + id + " not found");
        } else {
            old_car.get().setMake(new_car.getMake());
            old_car.get().setModel(new_car.getModel());
            old_car.get().setYear(new_car.getYear());
            old_car.get().setPrice(new_car.getPrice());
            old_car.get().setVin(new_car.getVin());

            return carRepository.save(old_car.get());
        }
    }
}
