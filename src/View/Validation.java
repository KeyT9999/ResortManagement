
package View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Validation {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Scanner scanner = new Scanner(System.in);
    
    // hàm nhập chuỗi, có tác dụng hiện thị lời nhắc prompt, nhận dữ liệu từ người dùng, và xóa khoảng trắng dư thừa trim()
    public static String getString(String prompt) {
        System.out.println(prompt + ": ");
        return scanner.nextLine().trim();
    }
    // VD: nếu nhập " Kim Thang" thì kq ra :"Kim Thang"
    
    /* Kiếm tra chuỗi k rỗng và k có k trắng, VD nếu nhập chuối rỗng hoặc khoảng trắng thì block và in ra nhập sai */
    public static String validateString(String prompt, String errorMsg) {
        String input;
        while(true) {
            input = getString(prompt);
            if(!input.isEmpty() && input.matches("^[\\S]*$")) {
                return input;
            } else {
                printErrorMessage(errorMsg);
            }
        }
    }
    
    // Xacs nhan số thực nhập lớn hơn 1 gtri tối thiểu
    public static double validateDouble(String prompt, String errorMsg, double minValue) {
        double value;
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                printErrorMessage(errorMsg + " - Input cannot be empty.");
                continue;
            }

            try {
                value = Double.parseDouble(input);
                if (value > minValue) {
                    return value;
                } else {
                    printErrorMessage(errorMsg + " - Value must be greater than " + minValue + ".");
                }
            } catch (NumberFormatException e) {
                printErrorMessage(errorMsg + " - Please enter a valid number.");
            }
        }
    }
    
    // Xacs nhan số nguyen nhập lớn hơn 1 gtri tối thiểu
    public static int validateInteger(String prompt, String errorMsg, Integer minValue) {
        int value;
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                printErrorMessage(errorMsg + " - Input cannot be empty.");
                continue;
            }

            try {
                value = Integer.parseInt(input);
                if (minValue == null || value >= minValue) {
                    return value;
                } else {
                    printErrorMessage(errorMsg + " - Value must be at least " + minValue + ".");
                }
            } catch (NumberFormatException e) {
                printErrorMessage(errorMsg + " - Please enter a valid integer.");
            }
        }
    }
    
    // Sửa tên, chuyển chữ cái đầu sang viết hoa
    public static String normalizeName(String name) {
        String cleanedName = name.toLowerCase().replaceAll("[^\\p{L}\\d\\s]", "").trim();
        cleanedName = cleanedName.replaceAll("[,\\s]+", " ");
        StringJoiner normalized = new StringJoiner(" ");
        for (String word : cleanedName.split(" ")) {
            if (!word.isEmpty()) {
                normalized.add(Character.toUpperCase(word.charAt(0)) + word.substring(1));
            }
        }
        return normalized.toString();
    }
    
    // In thông báo lỗi
    public static void printErrorMessage(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }
    
    // xác thực đầu vào chuỗi với các điều kiện cụ thể
    public static String validateStringInput(String prompt, String errorMsg) {
        String input;
        while (true) {
            input = getString(prompt);
            if (!input.isEmpty() && input.matches("^[\\p{L} .'-]+$")) {
                return input;
            } else {
                printErrorMessage(errorMsg + " - Please enter a valid string without numbers or special characters.");
            }
        }
    }
    
    //  xác nhận ID với regex
    public static String validateID(String prompt, String errorMsg, String regex) {
        String input;
        while (true) {
            input = getString(prompt);
            if (input.isEmpty() || !input.matches(regex)) {
                printErrorMessage(errorMsg + " - ID must match the format and cannot be empty.");
            } else {
                return input;
            }
        }
    }
    
    // Kiem tra so dien thoai hop le
    public static String validatePhoneNumber(String prompt, String errorMsg) {
        String input;
        while (true) {
            input = getString(prompt);
            if (input.isEmpty() || !input.matches("^\\d{10}$")) {
                printErrorMessage(errorMsg + " - Phone number must be exactly 10 digits and cannot be empty.");
            } else {
                return input;
            }
        }
    }
    
    // // Ngày phân tích từ chuỗi
    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            printErrorMessage("Invalid Date Format, Please Try (yyyy-MM-dd)");
            return null;
        }
    }
    
    // Kiem tra email
    public static String validateEmail(String prompt, String errorMsg) {
        String input;
        while (true) {
            input = getString(prompt);
            if (input.isEmpty() || !input.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,3}$")) {
                printErrorMessage(errorMsg + " - Please enter a valid email address.");
            } else {
                return input;
            }
        }
    }
     // Convert LocalDate to String
    public static String localDateToString(LocalDate date) {
        return (date == null) ? null : date.format(DATE_FORMATTER);
    }

    // Kiem tra ngay sinh
    public static LocalDate validateDateOfBirth(String prompt, String errorMsg) {
        LocalDate parsedDate;
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                printErrorMessage(errorMsg + " - Input cannot be empty.");
                continue;
            }

            try {
                parsedDate = LocalDate.parse(input, DATE_FORMATTER);
                if (isAgeValid(parsedDate)) {
                    return parsedDate;
                } else {
                    printErrorMessage("You must be at least 18 years old.");
                }
            } catch (DateTimeParseException e) {
                printErrorMessage(errorMsg + " - Date must be in the format (yyyy-MM-dd)");
            }
        }
    }

    // Xac thuc ngay dat phong
    public static LocalDate validateBookingDate(String prompt, String errorMsg) {
        LocalDate parsedDate;
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                printErrorMessage(errorMsg + " - Input cannot be empty.");
                continue;
            }

            try {
                parsedDate = LocalDate.parse(input, DATE_FORMATTER);
                if (isFutureDate(parsedDate)) {
                    return parsedDate;
                } else {
                    printErrorMessage("The booking date must be in the future.");
                }
            } catch (DateTimeParseException e) {
                printErrorMessage(errorMsg + " - Date must be in the format (yyyy-MM-dd)");
            }
        }
    }
    

    // Xác thực ngày bắt đầu
    public static LocalDate validateStartDate(LocalDate bookingDate, String prompt, String errorMsg) {
        LocalDate parsedDate;
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                printErrorMessage(errorMsg + " - Input cannot be empty.");
                continue;
            }

            try {
                parsedDate = LocalDate.parse(input, DATE_FORMATTER);
                if (parsedDate.isAfter(bookingDate)) {
                    return parsedDate;
                } else {
                    printErrorMessage("The start date must be after the booking date.");
                }
            } catch (DateTimeParseException e) {
                printErrorMessage(errorMsg + " - Date must be in the format (yyyy-MM-dd)");
            }
        }
    }

    // Xac thuc ngay ket thuc
    public static LocalDate validateEndDate(LocalDate startDate, String prompt, String errorMsg) {
        LocalDate parsedDate;
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                printErrorMessage(errorMsg + " - Input cannot be empty.");
                continue;
            }

            try {
                parsedDate = LocalDate.parse(input, DATE_FORMATTER);
                if (parsedDate.isAfter(startDate)) {
                    return parsedDate;
                } else {
                    printErrorMessage("The end date must be after the start date.");
                }
            } catch (DateTimeParseException e) {
                printErrorMessage(errorMsg + " - Date must be in the format (yyyy-MM-dd)");
            }
        }
    }

    // Kiểm tra xem ngày có trong tương lai không
    private static boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    // Kiểm tra xem tuổi có hợp lệ không (18 tuổi trở lên)
    private static boolean isAgeValid(LocalDate BOD) {
        LocalDate today = LocalDate.now();
        int age = today.getYear() - BOD.getYear();
        if (today.getMonthValue() < BOD.getMonthValue() ||
                (today.getMonthValue() == BOD.getMonthValue() && today.getDayOfMonth() < BOD.getDayOfMonth())) {
            age--;
        }
        return age >= 18;
    }

    // Xác nhận giới tính
    public static String validateGender(String prompt, String errorMsg) {
        String input;
        while (true) {
            input = getString(prompt);
            if (input.isEmpty() || (!input.equalsIgnoreCase("Male") && !input.equalsIgnoreCase("Female"))) {
                printErrorMessage(errorMsg + " - Please enter either 'Male' or 'Female'.");
            } else {
                return input;
            }
        }
    }
    
   // xác thực số thẻ ID
    public static String validateIDCard(String prompt, String errorMsg) {
        String input;
        while (true) {
            input = getString(prompt);
            if (input.isEmpty() || (!input.matches("^\\d{9}$") && !input.matches("^\\d{12}$"))) {
                printErrorMessage(errorMsg + " - ID Card must be 9 or 12 digits");
            } else {
                return input;
            }
        }
    }
    
}
