package Model;

import java.util.Objects;

public abstract class Facility {
//    Các loại dịch vụ này sẽ bao có các thông tin: Mã dịch vụ, Tên dịch vụ, Diện tích sử dụng,
//    Chi phí thuê, Số lượng người tối đa, Kiểu thuê (bao gồm thuê theo năm, tháng, ngày,
//                                                    giờ.

    private String facilityID; // mã dv
    private String facilityName;// tên dv
    private double area;// dtich
    private double rentalCost;// chi phi thue
    private int maxPeople;//so luong toi da nguoi
    private String rentalType;// kieu thue( thang nam gio)

    public Facility(String facilityID, String facilityName, double area, double rentalCost, int maxPeople, String rentalType) {
        this.facilityID = facilityID;
        this.facilityName = facilityName;
        this.area = area;
        this.rentalCost = rentalCost;
        this.maxPeople = maxPeople;
        this.rentalType = rentalType;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(double rentalCost) {
        this.rentalCost = rentalCost;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }


    public String displayInfo() {
        return String.format("Facility ID: %s, Name: %s, Area: %.2f, Rental Cost: %.2f, Max People: %d, Rental Type: %s",
                facilityID, facilityName, area, rentalCost, maxPeople, rentalType);
    }

    @Override
    public String toString() {
        return "Facility{" +
                "facilityID='" + facilityID + '\'' +
                ", facilityName='" + facilityName + '\'' +
                ", area=" + area +
                ", rentalCost=" + rentalCost +
                ", maxPeople=" + maxPeople +
                ", rentalType='" + rentalType + '\'' +
                '}';
    }
}
