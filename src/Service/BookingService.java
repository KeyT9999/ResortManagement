package Service;

import Model.Booking;
import Model.Facility;
import Repository.BookingRepository;
import View.Validation;

import java.time.LocalDate;
import java.util.TreeSet;

public class BookingService implements IBookingService {
    private TreeSet<Booking> bookingList;
    private final BookingRepository bkRepository;
    private final String errMsg;
    private final CustomerService customerService;
    private final FacilityService facilityService;

    public BookingService() {
        bkRepository = new BookingRepository();
        bookingList = bkRepository.readFile();
        customerService = new CustomerService();
        facilityService = new FacilityService();
        errMsg = "-> Invalid Input, Try Again.";
    }

    public TreeSet<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(TreeSet<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public void display() {
        if (bookingList.isEmpty()) {
            System.out.println("-> The List Is Empty.");
            return;
        }

        System.out.println("+------------+-----------------+--------------+--------------+-----------------+--------------+");
        System.out.printf("| %-10s | %-15s | %-12s | %-12s | %-15s | %-12s |\n",
                "Booking ID", "Booking Date", "Start Date", "End Date", "Customer ID", "Service ID");
        System.out.println("+------------+-----------------+--------------+--------------+-----------------+--------------+");

        for (Booking booking : bookingList) {
            System.out.printf("| %-10s | %-15s | %-12s | %-12s | %-15s | %-12s |\n",
                    booking.getBookingID(),
                    Validation.localDateToString(booking.getBookingDate()),
                    Validation.localDateToString(booking.getStartDate()),
                    Validation.localDateToString(booking.getEndDate()),
                    booking.getCustomerID(),
                    booking.getServiceID());
        }

        System.out.println("+------------+-----------------+--------------+--------------+-----------------+--------------+");
    }

    @Override
    public void add(Booking booking) {
        try {
            bookingList.add(booking);
            System.out.println("-> Booking Added Successfully!");
        } catch (Exception e) {
            System.out.println("-> Error While Adding Booking: " + e.getMessage());
        }
    }

    @Override
    public void save() {
        try {
            bkRepository.writeFile(bookingList);
            System.out.println("-> Booking Saved Successfully!");
        } catch (Exception e) {
            System.out.println("-> Error While Saving Data: " + e.getMessage());
        }
    }

    @Override
    public void update(Booking updatedBooking) {
        try {
            Booking existingBooking = findByID(updatedBooking.getBookingID());
            if (existingBooking != null) {
                bookingList.remove(existingBooking);
                bookingList.add(updatedBooking);
                System.out.println("-> Booking ID " + updatedBooking.getBookingID() + " Updated Successfully!");
            } else {
                System.out.println("-> Booking Not Found.");
            }
        } catch (Exception e) {
            System.out.println("-> Error While Updating Booking: " + e.getMessage());
        }
    }

    @Override
    public Booking findByID(String ID) {
        for (Booking b : bookingList) {
            if (b.getBookingID().equalsIgnoreCase(ID)) {
                return b;
            }
        }
        return null;
    }
    /**
 * Kiểm tra xem Facility có bị đặt trùng lịch không
 */
public boolean isFacilityBooked(String faciID, LocalDate startDate, LocalDate endDate) {
    for (Booking booking : bookingList) {
        if (booking.getServiceID().equalsIgnoreCase(faciID)) {
            LocalDate existingStart = booking.getStartDate();
            LocalDate existingEnd = booking.getEndDate();

            // Kiểm tra xem ngày đặt có trùng hoặc giao với booking đã tồn tại không
            boolean isOverlap = (startDate.isBefore(existingEnd) && endDate.isAfter(existingStart)) ||
                                (startDate.isEqual(existingStart) || endDate.isEqual(existingEnd));

            if (isOverlap) {
                System.out.println("-> Facility " + faciID + " is already booked from " +
                        existingStart + " to " + existingEnd + ".");
                return true;
            }
        }
    }
    return false;
}
/**
 * Tính ngày End Date dựa vào Rental Type
 */
public LocalDate calculateEndDate(LocalDate startDate, String rentalType) {
    return switch (rentalType) {
        case "day" -> startDate.plusDays(1);
        case "week" -> startDate.plusWeeks(1);
        case "month" -> startDate.plusMonths(1);
        default -> null;
    };
}



   public void addBooking() {
    try {
        do {
            // Hiển thị danh sách khách hàng để chọn ID
            customerService.display();
            String cusID;
            do {
                cusID = Validation.validateID("Customer ID", errMsg, "^CUS-\\d{4}$");
                if (customerService.findByID(cusID) == null) {
                    System.out.println("-> Invalid Customer ID, Try Again.");
                }
            } while (customerService.findByID(cusID) == null);

            // Hiển thị danh sách Facility để chọn ID
            facilityService.display();
            String faciID;
            do {
                faciID = Validation.validateID("Facility ID", errMsg, "^SV(VL|HO|RO)-\\d{4}$");
                if (facilityService.findByID(faciID) == null) {
                    System.out.println("-> Invalid Facility ID, Try Again.");
                }
            } while (facilityService.findByID(faciID) == null);

            // Nhập ngày đặt và ngày bắt đầu
            LocalDate bookingDate = Validation.validateBookingDate("Booking Date", errMsg);
            LocalDate startDate = Validation.validateStartDate(bookingDate, "Start Date", errMsg);

            // Nhập kiểu thuê (day/week/month/year)
            String rentalType;
            LocalDate endDate;
            int duration;
            do {
                rentalType = Validation.validateStringInput("Choose Rental Type (day/month/year): ", errMsg).toLowerCase();
                
                switch (rentalType) {
                    case "day" -> {
                        duration = Validation.validateInteger("How many days do you want to stay?", errMsg, 1);
                        endDate = startDate.plusDays(duration);
                    }
                    case "month" -> {
                        duration = Validation.validateInteger("How many months do you want to stay?", errMsg, 1);
                        endDate = startDate.plusMonths(duration);
                    }
                    case "year" -> {
                        duration = Validation.validateInteger("How many years do you want to stay?", errMsg, 1);
                        endDate = startDate.plusYears(duration);
                    }
                    default -> {
                        System.out.println("-> Invalid Rental Type, Try Again.");
                        endDate = null;
                    }
                }
            } while (endDate == null);

            System.out.println("-> Your stay will end on: " + endDate);

            // Kiểm tra Facility đã được đặt trong khoảng thời gian này chưa
            if (isFacilityBooked(faciID, startDate, endDate)) {
                System.out.println("-> This facility has already been booked in this period, please choose another one.");
                continue;
            }

            // Nhập Booking ID (kiểm tra trùng)
            String bookingID;
            do {
                bookingID = Validation.validateID("Booking ID", errMsg, "^BK\\d{3}$");
                if (findByID(bookingID) != null) {
                    System.out.println("-> Duplicated Booking ID, Try Again.");
                }
            } while (findByID(bookingID) != null);

            // Thêm Booking vào hệ thống
            add(new Booking(bookingID, Validation.localDateToString(bookingDate),
                    Validation.localDateToString(startDate),
                    Validation.localDateToString(endDate), cusID, faciID));

            // Kiểm tra Facility và lưu thay đổi
            Facility foundFacility = facilityService.findByID(faciID);

            if (Validation.validateStringInput("-> Do you want to save changes to file (Y/N): ", errMsg).equalsIgnoreCase("Y")) {
                save();
            } else {
                System.out.println("-> Booking not saved.");
            }
        } while (Validation.validateStringInput("-> Do You Want To Continue Adding Booking (Y/N)", errMsg).equalsIgnoreCase("Y"));
    } catch (Exception e) {
        System.out.println("-> Error While Adding Booking: " + e.getMessage());
    }
}


}
