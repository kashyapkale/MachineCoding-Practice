package org.ParkingLot.models;

import lombok.Getter;
import lombok.Setter;
import org.ParkingLot.utils.Constants;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Getter
@Setter
public class Floor {
    private Integer floorNumber;
    private Map<VehicleType, Integer> vehicleToFreeSlotsMap;
    private Map<VehicleType, Integer> vehicleToSlotsStartIndexMap;
    private Map<VehicleType, Integer> vehicleToSlotsEndIndexMap;
    private List<ParkingSlot> parkingSlotList;
    private Integer totalNumberOfSlots;
    private Boolean isFloorFilled;


    public Floor(int floorNumber, int numberOfSlots){
        this.floorNumber = floorNumber;
        parkingSlotList = new ArrayList<>();
        vehicleToFreeSlotsMap = new HashMap<>();
        vehicleToSlotsStartIndexMap = new HashMap<>();
        vehicleToSlotsEndIndexMap = new HashMap<>();
        totalNumberOfSlots = numberOfSlots;
        isFloorFilled = false;

        int equalNumberOfSlots = numberOfSlots/3;
        vehicleToFreeSlotsMap.put(VehicleType.BIKE, equalNumberOfSlots);
        vehicleToFreeSlotsMap.put(VehicleType.CAR, equalNumberOfSlots);
        vehicleToFreeSlotsMap.put(VehicleType.TRUCK, numberOfSlots - (equalNumberOfSlots*2));

        parkingSlotList.add(null);
        VehicleType vehicleTypeSlot = null;
        for(int slot = 1; slot <= numberOfSlots; slot++){
            if(slot == 1){
                vehicleTypeSlot = VehicleType.BIKE;
                vehicleToSlotsStartIndexMap.put(VehicleType.BIKE, slot);
                vehicleToSlotsEndIndexMap.put(VehicleType.TRUCK, numberOfSlots);
            }

            if(slot > equalNumberOfSlots && slot <= equalNumberOfSlots * 2){
                vehicleTypeSlot = VehicleType.CAR;
                vehicleToSlotsStartIndexMap.put(VehicleType.CAR, slot);
                vehicleToSlotsEndIndexMap.put(VehicleType.BIKE, slot-1);
            }

            if(slot > equalNumberOfSlots * 2){
                vehicleTypeSlot = VehicleType.TRUCK;
                vehicleToSlotsStartIndexMap.put(VehicleType.TRUCK, slot);
                vehicleToSlotsEndIndexMap.put(VehicleType.CAR, slot-1);
            }

            parkingSlotList.add(new ParkingSlot(floorNumber, slot, vehicleTypeSlot, false, null));
        }
    }

    public Boolean checkIfAnySlotIsFree(){
        return vehicleToFreeSlotsMap.get(VehicleType.BIKE) != 0
                || vehicleToFreeSlotsMap.get(VehicleType.CAR) != 0
                || vehicleToFreeSlotsMap.get(VehicleType.TRUCK) != 0;
    }

    private void printFreeSlotsCountForVehicle(@NotNull VehicleType vehicleType){
        System.out.printf(
                (Constants.DISPLAY_ALL_FREE_SLOT_COUNT_MESSAGE) + "%n",
                vehicleType,
                floorNumber,
                vehicleToFreeSlotsMap.get(vehicleType));
    }

    private void printOccupiedSlotsCountForVehicle(@NotNull VehicleType vehicleType){
        System.out.printf(
                (Constants.DISPLAY_ALL_OCCUPIED_SLOT_COUNT_MESSAGE) + "%n",
                vehicleType,
                floorNumber,
                totalNumberOfSlots - vehicleToFreeSlotsMap.get(vehicleType));
    }

    private void printFreeSlotsForVehicle(@NotNull VehicleType vehicleType){
        StringBuilder freeSlots = new StringBuilder();
        int startSlot = vehicleToSlotsStartIndexMap.get(vehicleType);
        int endSlot = vehicleToSlotsEndIndexMap.get(vehicleType);
        for(int index = startSlot; index <= endSlot; index++){
            if(!parkingSlotList.get(index).getIsOccupied()){
                freeSlots.append(index).append(" ");
            }
        }

        System.out.printf(
                (Constants.DISPLAY_ALL_FREE_SLOT_MESSAGE) + "%n",
                vehicleType,
                floorNumber,
                freeSlots);
    }

    private void printOccupiedSlotsForVehicle(@NotNull VehicleType vehicleType){
        StringBuilder freeSlots = new StringBuilder();
        int startSlot = vehicleToSlotsStartIndexMap.get(vehicleType);
        int endSlot = vehicleToSlotsEndIndexMap.get(vehicleType);
        for(int index = startSlot; index <= endSlot; index++){
            if(parkingSlotList.get(index).getIsOccupied()){
                freeSlots.append(index).append(" ");
            }
        }

        System.out.printf(
                (Constants.DISPLAY_ALL_OCCUPIED_SLOT_MESSAGE) + "%n",
                vehicleType,
                floorNumber,
                freeSlots);
    }

    public void displayAllFreeSlotsCountForFloor(@NotNull VehicleType vehicleType){
        if(vehicleType.equals(VehicleType.ALL)){
            //Display for Bike
            printFreeSlotsCountForVehicle(VehicleType.BIKE);
            //Display for Car
            printFreeSlotsCountForVehicle(VehicleType.CAR);
            //Display for Truck
            printFreeSlotsCountForVehicle(VehicleType.TRUCK);
        } else {
            printFreeSlotsCountForVehicle(vehicleType);
        }
    }

    public void displayAllFreeSlotsForFloor(@NotNull VehicleType vehicleType){
        if(vehicleType.equals(VehicleType.ALL)){
            //Display for Bike
            printFreeSlotsForVehicle(VehicleType.BIKE);
            //Display for Car
            printFreeSlotsForVehicle(VehicleType.CAR);
            //Display for Truck
            printFreeSlotsForVehicle(VehicleType.TRUCK);
        } else {
            printFreeSlotsForVehicle(vehicleType);
        }
    }

    public void displayAllOccupiedSlotsCountForFloor(@NotNull VehicleType vehicleType){
        if(vehicleType.equals(VehicleType.ALL)){
            //Display for Bike
            printOccupiedSlotsCountForVehicle(VehicleType.BIKE);
            //Display for Car
            printOccupiedSlotsCountForVehicle(VehicleType.CAR);
            //Display for Truck
            printOccupiedSlotsCountForVehicle(VehicleType.TRUCK);
        } else {
            printOccupiedSlotsCountForVehicle(vehicleType);
        }
    }

    public void displayAllOccupiedSlotsForFloor(@NotNull VehicleType vehicleType){
        if(vehicleType.equals(VehicleType.ALL)){
            //Display for Bike
            printOccupiedSlotsForVehicle(VehicleType.BIKE);
            //Display for Car
            printOccupiedSlotsForVehicle(VehicleType.CAR);
            //Display for Truck
            printOccupiedSlotsForVehicle(VehicleType.TRUCK);
        } else {
            printOccupiedSlotsForVehicle(vehicleType);
        }
    }

    public ParkingSlot getNearestEmptySlotForVehicle(VehicleType vehicleType){
        int startSlot = vehicleToSlotsStartIndexMap.get(vehicleType);
        int endSlot = vehicleToSlotsEndIndexMap.get(vehicleType);
        for(int index = startSlot; index <= endSlot; index++){
            if(!parkingSlotList.get(index).getIsOccupied()){
                return parkingSlotList.get(index);
            }
        }

        return null;
    }

    public void parkVehicleInSlot(ParkingSlot slot){
        VehicleType vehicleType = slot.getVehicleTypeSlot();
        vehicleToFreeSlotsMap.put(vehicleType, vehicleToFreeSlotsMap.get(vehicleType) - 1);
        if(!checkIfAnySlotIsFree()){
            isFloorFilled = true;
        }
    }

    public void unParkVehicleInSlot(ParkingSlot slot){
        VehicleType vehicleType = slot.getVehicleTypeSlot();
        vehicleToFreeSlotsMap.put(vehicleType, vehicleToFreeSlotsMap.get(vehicleType) + 1);
        isFloorFilled = false;
    }

}