package org.ParkingLot;

import org.ParkingLot.models.VehicleType;
import org.ParkingLot.services.ParkingLotService;
import org.ParkingLot.services.ParkingLotServiceImpl;
import org.ParkingLot.utils.Commands;
import org.ParkingLot.utils.Constants;

import java.lang.constant.ConstantDescs;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Welcome to Parking management system !");
        final Scanner scanner = new Scanner(System.in);
        ParkingLotService parkingLotService = new ParkingLotServiceImpl();
        while(true){
            String input = scanner.nextLine();

            if(input.equalsIgnoreCase(Constants.EXIT)){
                System.out.println(Constants.GOOD_BYE_MESSAGE);
                break;
            }

            String[] query = input.trim().split(" ");
            try{
                switch(query[0]){

                    case Commands.CREATE_PARKING_LOT : {
                        parkingLotService.createParkingLot(
                                query[1],
                                Integer.parseInt(query[2]),
                                Integer.parseInt(query[3]));
                    }
                    break;

                    case Commands.DISPLAY : {
                        if(query[1].equals(Commands.FREE_COUNT)){
                            parkingLotService.displayFreeCount(VehicleType.valueOf(query[2]));
                        } else if(query[1].equals(Commands.FREE_SLOTS)){
                            parkingLotService.displayFreeSlot(VehicleType.valueOf(query[2]));
                        } else if(query[1].equals(Commands.OCCUPIED_SLOTS)){
                            parkingLotService.displayOccupiedSlot(VehicleType.valueOf(query[2]));
                        }
                    }
                    break;

                    case Commands.PARK_VEHICLE : {
                        parkingLotService.parkVehicle(query[2], query[3], VehicleType.valueOf(query[1]));
                    }
                    break;

                    case Commands.UN_PARK_VEHICLE: {
                        parkingLotService.unparkVehicle(query[1]);
                    }
                    break;
                }
            } catch(RuntimeException e){
                System.out.println(e.toString());
            }
        }
    }
}