
package Model;

import View.Validation;
import java.time.LocalDate;
import java.util.Objects;


public class Booking implements Comparable<Booking>{
    private String bookingID; // 1 lượt đặt phòng là 1 id
    private LocalDate bookingDate; // ngày book
    private LocalDate startDate; // ngày khách bắt đầu
    private LocalDate endDate; // ngày khách keeys thúc
    private String customerID; // mã khách hàng
    private String serviceID; // mã dvu khách đặt

    public Booking(String bookingID, String bookingDate, String startDate, String endDate, String customerID, String serviceID) {
        this.bookingID = bookingID;
        this.bookingDate = Validation.parseDate(bookingDate);
        this.startDate = Validation.parseDate(startDate);
        this.endDate = Validation.parseDate(endDate);
        this.customerID = customerID;
        this.serviceID = serviceID;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }
    
    // So sánh thứ tự booking, sắp xếp danh sách theo startDate
    // Nếu startDate trùng nhau, so sánh theo bookingID (mã đặt p)
    @Override
    public int compareTo(Booking o) {
        int dateComparision = this.startDate.compareTo(o.startDate);
        if (dateComparision == 0){
            return this.bookingID.compareTo(o.bookingID);
        }
        return dateComparision;
    }

    // Kiểm tra xem 2 booking có giống nhau không ? so sánh booking id của 2 booking, nếu cùng id thì là giống nhau
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingID.equals(booking.bookingID);
    }

    // tránh trùng lặp
    @Override
    public int hashCode() {
        return Objects.hash(bookingID);
    }
    
    
    
}
