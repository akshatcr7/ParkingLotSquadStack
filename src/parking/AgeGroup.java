package parking;

import java.util.ArrayList;

public class AgeGroup {
    public int age;
    public ArrayList<Vehicle> vehicles;

    public AgeGroup(int age) {
        this.age = age;
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }
}
