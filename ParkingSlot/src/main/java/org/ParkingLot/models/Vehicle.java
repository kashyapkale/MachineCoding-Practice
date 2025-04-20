package org.ParkingLot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Vehicle {
    private String licensePlate;
    private String color;
    private VehicleType vehicleType;
}
