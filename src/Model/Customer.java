package Model;

import java.time.LocalDate;

public class Customer extends Person implements Comparable<Customer> {

    private String address; // địa chỉ của khách hàng
    private String customerType; // loại khách hàng (Diamond, Platinum, Gold, Silver, Member).

    public Customer(String ID, String fullname, LocalDate DOB, boolean gender, String CMND, String phoneNumber, String email, String address, String customerType) {
        super(ID, fullname, DOB, gender, CMND, phoneNumber, email);
        this.address = address;
        this.customerType = customerType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @Override
    public int compareTo(Customer o) {
        return this.getID().compareTo(o.getID());
    }

    @Override
    public String toString() {
        return super.toString() + "Customer{" +
                "address='" + address + '\'' +
                ", customerType='" + customerType + '\'' +
                '}';
    }
}
