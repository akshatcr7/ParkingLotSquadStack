package parking;

public class ParkingSlot {
    public int id;
    public AgeGroup ageGroup;
    public Vehicle vehicle;

    public ParkingSlot(int id, AgeGroup ageGroup, Vehicle vehicle) {
        this.id = id;
        this.ageGroup = ageGroup;
        this.vehicle = vehicle;
    }
}
