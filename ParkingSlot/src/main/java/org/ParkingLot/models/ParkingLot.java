package org.ParkingLot.models;

import lombok.Getter;
import lombok.Setter;
import org.ParkingLot.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ParkingLot {
    private String parkingLotId;
    private List<Floor> floors;
    private Integer numberOfFloors;
    private Map<String, ParkingSlot> ticketIdToParkingSlotMap;

    public ParkingLot(String parkingLotId, Integer numberOfFloors, Integer numberOfSlots){
        this.parkingLotId = parkingLotId;
        floors = new ArrayList<Floor>();
        floors.add(null);
        for(int floor = 1; floor <= numberOfFloors; floor++){
            floors.add(new Floor(floor, numberOfSlots));
        }
        ticketIdToParkingSlotMap = new HashMap<String, ParkingSlot>();
    }

    public void displayAllFreeSlotsCount(){
        for(Floor floor : floors){
            if(floor != null){
                floor.displayAllFreeSlotsCountForFloor(VehicleType.ALL);
            }
        }
    }

    public void displayAllFreeSlotsCountForFloorAndVehicle(Integer floorNumber, VehicleType vehicleType){
        floors.get(floorNumber).displayAllFreeSlotsCountForFloor(vehicleType);
    }

    public void displayAllFreeSlotsCountForVehicle(VehicleType vehicleType){
        for(Floor floor : floors){
            if(floor != null) {
                floor.displayAllFreeSlotsCountForFloor(vehicleType);
            }
        }
    }

    public void displayAllFreeSlots(){
        for(Floor floor : floors){
            if(floor != null){
                floor.displayAllFreeSlotsForFloor(VehicleType.ALL);
            }
        }
    }

    public void displayAllFreeSlotsForFloorAndVehicle(Integer floorNumber, VehicleType vehicleType){
        floors.get(floorNumber).displayAllFreeSlotsForFloor(vehicleType);
    }

    public void displayAllFreeSlotsForVehicle(VehicleType vehicleType){
        for(Floor floor : floors){
            if(floor != null){
                floor.displayAllFreeSlotsForFloor(vehicleType);
            }
        }
    }

    public void displayAllOccupiedSlotsCount(){
        for(Floor floor : floors){
            if(floor != null){
                floor.displayAllOccupiedSlotsCountForFloor(VehicleType.ALL);
            }
        }
    }

    public void displayAllOccupiedSlotsCountForFloorAndVehicle(Integer floorNumber, VehicleType vehicleType){
        floors.get(floorNumber).displayAllOccupiedSlotsCountForFloor(vehicleType);
    }

    public void displayAllOccupiedSlotsCountForVehicle(VehicleType vehicleType){
        for(Floor floor : floors){
            if(floor != null){
                floor.displayAllOccupiedSlotsCountForFloor(vehicleType);
            }
        }
    }

    public void displayAllOccupiedSlots(){
        for(Floor floor : floors){
            if(floor != null){
                floor.displayAllOccupiedSlotsForFloor(VehicleType.ALL);
            }
        }
    }

    public void displayAllOccupiedSlotsForFloorAndVehicle(Integer floorNumber, VehicleType vehicleType){
        floors.get(floorNumber).displayAllOccupiedSlotsForFloor(vehicleType);
    }

    public void displayAllOccupiedSlotsForVehicle(VehicleType vehicleType){
        for(Floor floor : floors){
            if(floor != null){
                floor.displayAllOccupiedSlotsForFloor(vehicleType);
            }
        }
    }

    private ParkingSlot getNearestFreeEmptySlotForVehicle(VehicleType vehicleType){
        for(Floor floor : floors){
            if(floor != null){
                ParkingSlot nearestEmptySlot = floor.getNearestEmptySlotForVehicle(vehicleType);
                if(nearestEmptySlot != null){
                    return nearestEmptySlot;
                }
            }
        }

        return null;
    }

    public StringBuilder parkVehicle(Vehicle vehicle){
        ParkingSlot parkingSlot = getNearestFreeEmptySlotForVehicle(vehicle.getVehicleType());
        StringBuilder ticketId = null;
        if(parkingSlot != null){
            parkingSlot.setVehicle(vehicle);
            parkingSlot.setIsOccupied(true);
            floors.get(parkingSlot.getFloorNumber()).parkVehicleInSlot(parkingSlot);
            System.out.printf(Constants.PRINT_PARKING_SUCCESSFUL_MESSAGE + '\n',
                    parkingSlot.getFloorNumber(),
                    parkingSlot.getSlotNumber());
            ticketId = new StringBuilder(
                    parkingLotId + "_" + parkingSlot.getFloorNumber() + "_" + parkingSlot.getSlotNumber()
            );
            ticketIdToParkingSlotMap.put(String.valueOf(ticketId), parkingSlot);
        } else{
            System.out.println(Constants.PRINT_PARKING_FULL_MESSAGE);
        }

        return ticketId;
    }

    public void unParkVehicle(String ticketId){
        ParkingSlot parkingSlot = ticketIdToParkingSlotMap.get(ticketId);
        parkingSlot.setIsOccupied(false);
        parkingSlot.setVehicle(null);
        floors.get(parkingSlot.getFloorNumber()).unParkVehicleInSlot(parkingSlot);
        System.out.println(Constants.PRINT_UN_PARKING_FULL_MESSAGE);
    }
}
