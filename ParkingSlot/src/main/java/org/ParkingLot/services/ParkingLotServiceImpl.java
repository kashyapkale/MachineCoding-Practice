package org.ParkingLot.services;

import org.ParkingLot.models.ParkingLot;
import org.ParkingLot.models.Vehicle;
import org.ParkingLot.models.VehicleType;
import org.ParkingLot.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotServiceImpl implements ParkingLotService{
    private final Map<String, ParkingLot> parkingLotIdToParkingLotMap;
    private ParkingLot parkingLotInUse;

    public ParkingLotServiceImpl(){
        parkingLotIdToParkingLotMap = new HashMap<>();
        parkingLotInUse = null;
    }

    public void updateParkingLotInUse(String parkingLotId){
        parkingLotInUse = parkingLotIdToParkingLotMap.get(parkingLotId);
    }

    @Override
    public void createParkingLot(String parkingLotId,
                                 Integer numberOfFloors,
                                 Integer numberOfSlots) {
        ParkingLot parkingLot = new ParkingLot(parkingLotId, numberOfFloors, numberOfSlots);
        parkingLotIdToParkingLotMap.put(parkingLotId, parkingLot);
        updateParkingLotInUse(parkingLotId);
        System.out.printf(Constants.SUCCESSFULLY_CREATED_PARKING_LOT_MESSAGE + "\n", parkingLotId);
    }



    @Override
    public void deleteParkingLot() {

    }

    @Override
    public void displayFreeCount(VehicleType vehicleType) {
        parkingLotInUse.displayAllFreeSlotsCountForVehicle(vehicleType);
    }

    @Override
    public void displayFreeSlot(VehicleType vehicleType) {
        parkingLotInUse.displayAllFreeSlotsForVehicle(vehicleType);
    }

    @Override
    public void displayOccupiedCount(VehicleType vehicleType) {
        parkingLotInUse.displayAllOccupiedSlotsCountForVehicle(vehicleType);
    }

    @Override
    public void displayOccupiedSlot(VehicleType vehicleType) {
        parkingLotInUse.displayAllOccupiedSlotsForVehicle(vehicleType);
    }

    @Override
    public void parkVehicle(String licensePlate, String color, VehicleType vehicleType) {
        Vehicle vehicle = new Vehicle(licensePlate, color, vehicleType);
        String ticket = String.valueOf(parkingLotInUse.parkVehicle(vehicle));
        System.out.printf(Constants.PRINT_PARKING_SUCCESSFUL_TICKET_ID + "\n", ticket);
    }

    @Override
    public void unparkVehicle(String ticketId) {
        parkingLotInUse.unParkVehicle(ticketId);
    }
}
