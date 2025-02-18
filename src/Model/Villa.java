package Model;

public class Villa extends Facility{
    private String roomStandard; // Tiêu chuẩn của phòng biệt thự (3-star, 4-star, Luxury, ...).
    private double poolArea; // Diện tích hồ bơi đi kèm (đơn vị: m²).
    private int numberOfFloor; //Số tầng của biệt thự.

    public Villa(String facilityID, String facilityName, double area, double rentalCost, int maxPeople, String rentalType, String roomStandard, double poolArea, int floorNumber) {
        super(facilityID, facilityName, area, rentalCost, maxPeople, rentalType);
        this.roomStandard = roomStandard;
        this.poolArea = poolArea;
        this.numberOfFloor = floorNumber;
    }

    public String getRoomStandard() {
        return roomStandard;
    }

    public void setRoomStandard(String roomStandard) {
        this.roomStandard = roomStandard;
    }

    public double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(double poolArea) {
        this.poolArea = poolArea;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    @Override
    public String toString() {
        return super.toString() + "Villa{" +
                "roomStandard='" + roomStandard + '\'' +
                ", poolArea=" + poolArea +
                ", numberOfFloor=" + numberOfFloor +
                '}';
    }
}
