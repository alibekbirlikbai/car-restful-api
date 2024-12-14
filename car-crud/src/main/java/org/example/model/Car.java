package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.Year;
import java.util.UUID;

@Entity(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    @NotEmpty(message = "make не должен быть пустым")
    private String make;

    @Column
    @NotEmpty(message = "model не должен быть пустым")
    private String model;

    @Column
    @Min(value = 1886, message = "year не должен быть до 1886")
    @Max(value = Year.MAX_VALUE, message = "year не должен превышать текущий год")
    private int year;

    @Column
    @Positive(message = "price должен быть положительным")
    private double price;

    @Column
    @Size(min = 17, max = 17, message = "vin должен быть длиной ровно 17 символов")
    private String vin;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
