package Model;

public class Room extends Facility{
    private String freeService; // Dịch vụ miễn phí đi kèm với phòng (VD: "Free Breakfast", "Free Pool Access").

    public Room(String facilityID, String facilityName, double area, double rentalCost, int maxPeople, String rentalType, String freeService) {
        super(facilityID, facilityName, area, rentalCost, maxPeople, rentalType);
        this.freeService = freeService;
    }

    public String getFreeService() {
        return freeService;
    }

    public void setFreeService(String freeService) {
        this.freeService = freeService;
    }

    @Override
    public String toString() {
        return super.toString() + "Room{" +
                "freeService='" + freeService + '\'' +
                '}';
    }
}
