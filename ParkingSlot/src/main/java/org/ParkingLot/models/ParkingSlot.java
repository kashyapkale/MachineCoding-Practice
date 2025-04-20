package org.ParkingLot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ParkingSlot {
    private final Integer floorNumber;
    private final Integer slotNumber;
    private final VehicleType vehicleTypeSlot;
    private Boolean isOccupied;
    private Vehicle vehicle;
}
