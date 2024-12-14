package org.example.service;

import org.example.exception.CarNotFoundException;
import org.example.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CarService {
    List<Car> findAll();
    Car findById(UUID id) throws CarNotFoundException;
    Car save(Car car);
    void deleteById(UUID id) throws CarNotFoundException;
    Car update(UUID id, Car car) throws CarNotFoundException;

}
