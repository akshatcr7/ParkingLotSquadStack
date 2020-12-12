package parking;

import java.util.ArrayList;
import java.util.HashMap;

public class ParkingLot {
    private int slots;
    HashMap<String, Vehicle> vehicleMap;
    public AgeGroup[] ageGroups;
    public ParkingSlot[] parkingslots;

    /**
     * Creates a parking lot with number of slots
     * @param count
     */
    public void createParkingLot(String count) {
        this.slots = Integer.parseInt(count);
        vehicleMap = new HashMap<>();
        ageGroups = new AgeGroup[200];
        parkingslots = new ParkingSlot[slots+1];
        System.out.println("Created parking of " + slots + " slots");
    }

    /**
     * Parks a vehicle
     * @param registrationNumber
     * @param age
     * @throws Exception
     */
    public void park(String registrationNumber, Integer age) throws Exception{
        if(ageGroups[age] == null) {
            ageGroups[age] = new AgeGroup(age);
        }
        Vehicle vehicle = new Vehicle(registrationNumber);
        if(vehicleMap.containsKey(vehicle.registrationNumber)) {
            System.out.println("Car with vehicle registration number \"" + registrationNumber + "\" is already parked");
            return;
        }
        int availableSlot = getAvailableSlots();
        parkingslots[availableSlot] = new ParkingSlot(availableSlot, ageGroups[age], vehicle);
        vehicle.slot = parkingslots[availableSlot];
        vehicleMap.putIfAbsent(registrationNumber, vehicle);
        ageGroups[age].addVehicle(vehicle);
        System.out.println("Car with vehicle registration number \"" + registrationNumber + "\" has been parked at slot number " + availableSlot);
    }

    /**
     * Vacates the parking slot
     * @param slotNumber
     * @throws Exception
     */
    public void leave(String slotNumber) throws Exception {
        int index = Integer.parseInt(slotNumber);
        if (parkingslots[index] == null) {
            throw new Exception("Slot already empty");
        }
        Vehicle vehicle = parkingslots[index].vehicle;
        int age = parkingslots[index].ageGroup.age;
        parkingslots[index].ageGroup.removeVehicle(vehicle);
        vehicleMap.remove(vehicle.registrationNumber);
        parkingslots[index] = null;
        System.out.println("Slot number " + index + " vacated, the car with vehicle registration number \"" + vehicle.registrationNumber + "\" left the space, the driver of the car was of age " + age);
    }

    /**
     * Get all registartion number for a given age
     * @param age
     * @throws Exception
     */
    public void getRegistrationNumbersForAge(String age) throws Exception{
        if (ageGroups[Integer.parseInt(age)] == null) {
            throw new Exception("No registration numbers for given age");
        }else {
            ArrayList<String> registrationNumbers = new ArrayList<>();
            for (Vehicle vehicle : ageGroups[Integer.parseInt(age)].vehicles) {
                registrationNumbers.add(vehicle.registrationNumber);
            }
            System.out.println(String.join(", ", registrationNumbers));
        }
    }

    /**
     * Get Slot number for a given age
     * @param age
     * @throws Exception
     */
    public void getSlotNumbersForAge(String age) throws Exception{
        if (ageGroups[Integer.parseInt(age)] == null) {
            throw new Exception("No Slot exists for given age");
        } else {
            ArrayList<String> slots = new ArrayList<>();
            for (Vehicle vehicle : ageGroups[Integer.parseInt(age)].vehicles) {
                slots.add(Integer.toString(vehicle.slot.id));
            }
            System.out.println(String.join(", ", slots));
        }
    }

    /**
     * Get slot number for a parked car
     * @param registrationNumber
     * @throws Exception
     */
    public void getSlotNumberFromRegNo(String registrationNumber) throws Exception{
        if(!vehicleMap.containsKey(registrationNumber)) {
            throw new Exception("Vehicle not parked");
        } else {
            int slot = vehicleMap.get(registrationNumber).slot.id;
            System.out.println(slot);
        }
    }

    /**
     * Get available parking slots
     * @return
     * @throws Exception
     */
    private int getAvailableSlots() throws Exception {
        for (int slotNumber=1; slotNumber<= slots; slotNumber++) {
            if (parkingslots[slotNumber] == null) {
                return slotNumber;
            }
        }
        throw new Exception("Slot Not Found");
    }
}