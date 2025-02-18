package Model;

import java.time.LocalDate;

// kế thừa person 
public class Employee extends Person{
    private String Degree; // trình độ học vấn nv
    private String Position; // chức vụ của nhân viên
    private double Salary; // mức lương nvien

    public Employee(String ID, String fullname, LocalDate DOB, boolean gender, String CMND, String phoneNumber, String email, String Degree, String position, double salary) {
        super(ID, fullname, DOB, gender, CMND, phoneNumber, email);
        this.Degree = Degree;
        this.Position = position;
        this.Salary = salary;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String Degree) {
        this.Degree = Degree;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        this.Position = position;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
            this.Salary = salary;
    }


}
