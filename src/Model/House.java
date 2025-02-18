package Model;

public class House extends Facility{
    private String roomStandard; //Tiêu chuẩn phòng của nhà (3-star, 4-star, Luxury, ...).
    private int numberOfFloor; // Số tầng của nhà.

    public House(String facilityID, String facilityName, double area, double rentalCost, int maxPeople, String rentalType, String roomStandard, int numberOfFloor) {
        super(facilityID, facilityName, area, rentalCost, maxPeople, rentalType);
        this.roomStandard = roomStandard;
        this.numberOfFloor = numberOfFloor;
    }

    public String getRoomStandard() {
        return roomStandard;
    }

    public void setRoomStandard(String roomStandard) {
        this.roomStandard = roomStandard;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    @Override
    public String toString() {
        return super.toString() + "House{" +
                "roomStandard='" + roomStandard + '\'' +
                ", numberOfFloor=" + numberOfFloor +
                '}';
    }
}