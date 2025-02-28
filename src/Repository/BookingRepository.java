package Repository;

import Model.Booking;
import View.Validation;

import java.io.*;
import java.util.TreeSet;

public class BookingRepository implements IBookingRepository{

    @Override
    public TreeSet<Booking> readFile() {
        TreeSet<Booking> bookingList = new TreeSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path + bookingPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 6) {
                    System.out.println("-> Skipping invalid line: " + line);
                    continue;
                }
                Booking booking = new Booking(data[0], data[1], data[2], data[3], data[4], data[5]);
                bookingList.add(booking);
            }
        } catch (IOException e) {
            System.out.println("-> Error reading file: " + e.getMessage());
        }
        return bookingList;
    }



    @Override
    public void writeFile(TreeSet<Booking> bookingList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + bookingPath, false))) { // false để ghi đè file cũ
            for (Booking booking : bookingList) {
                String line = booking.getBookingID() + ","
                        + Validation.localDateToString(booking.getBookingDate()) + ","
                        + Validation.localDateToString(booking.getStartDate()) + ","
                        + Validation.localDateToString(booking.getEndDate()) + ","
                        + booking.getCustomerID() + ","
                        + booking.getServiceID();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("-> Booking Saved Successfully!");
        } catch (IOException e) {
            System.out.println("-> Error While Writing To File: " + e.getMessage());
        }
    }


}
