package Model;

import View.Validation;

import java.time.LocalDate;

public abstract class Person {

    private String ID; //Mã định danh của cá nhân (Employee hoặc Customer)
    private String fullName; // Họ tên
    private LocalDate DOB; // Ngày sinh sd LocalDate
    private boolean Gender; //gtinh true= Male, false = female
    private String CMND; //cccd
    private String phoneNumber;
    private String Email;

    public Person() {
    }

    public Person(String ID, String fullname, LocalDate DOB, boolean gender, String CMND, String phoneNumber, String email) {
        this.ID = ID;
        this.fullName = fullname;
        this.DOB = DOB;
        this.Gender = gender;
        this.CMND = CMND;
        this.phoneNumber = phoneNumber;
        this.Email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean gender) {
        this.Gender = gender;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID='" + ID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", DOB=" + DOB +
                ", Gender=" + Gender +
                ", CMND='" + CMND + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
