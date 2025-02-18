package Repository;
//TASK 4
/*
quản lý dữ liệu khách hàng
đọc ghi file vào csv
nó tách biệt với Repository ( logic lưu trữ) và Service ( xử lí dữ liệu)
*/
import Model.Customer;
import View.Validation;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerRepository implements ICustomerRepository {

    public CustomerRepository(){};

    //ĐỌc danh sách
    @Override
    public ArrayList<Customer> readFile() {
        ArrayList<Customer> customerList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path + customerPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 9) {
                    continue;
                }
                try {
                    LocalDate DOB = Validation.parseDate(data[2]);   //chuyển String thành LocalDate (ngày sinh).
                    boolean gender = data[3].equalsIgnoreCase("Male"); //Chuyển giá trị "Male" hoặc "Female" thành true hoặc false (dùng equalsIgnoreCase()).
                    Customer customer = new Customer(
                            data[0], //ID
                            data[1],//Fullname
                            DOB, // DOB
                            gender, // Gender
                            data[4], //cccd
                            data[5], //phone number
                            data[6], //email
                            data[7], //address 
                            data[8]//customer type
                    );
                    customerList.add(customer);
                } catch (Exception e) {
                    System.out.println("Error parsing line: " + line + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("-> Error reading file: " + e.getMessage(), e);
        }
        return customerList;
    }

    @Override
    public void writeFile(ArrayList<Customer> entities) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + customerPath))) {
            for (Customer customer : entities) {
                String line = customer.getID() + ","
                        + customer.getFullName() + ","
                        + customer.getDOB() + ","
                        + (customer.isGender() ? "Male" : "Female") + ","
                        + customer.getCMND() + ","
                        + customer.getPhoneNumber() + ","
                        + customer.getEmail() + ","
                        + customer.getAddress() + ","
                        + customer.getCustomerType();

                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("-> Error writing to file: " + e.getMessage(), e);
        }
    }

}
