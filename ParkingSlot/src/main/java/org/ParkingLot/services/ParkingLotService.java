package org.ParkingLot.services;

import org.ParkingLot.models.Vehicle;
import org.ParkingLot.models.VehicleType;

public interface ParkingLotService {
    void createParkingLot(String parkingLotId, Integer numberOfFloors, Integer numberOfSlots);
    void deleteParkingLot();
    void displayFreeCount(VehicleType vehicleType);
    void displayFreeSlot(VehicleType vehicleType);
    void displayOccupiedCount(VehicleType vehicleType);
    void displayOccupiedSlot(VehicleType vehicleType);
    void parkVehicle(String licensePlate, String color, VehicleType vehicleType);
    void unparkVehicle(String ticketId);
}
