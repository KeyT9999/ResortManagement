package Model;

public class Contract {
    private int contractNum; // Số hơp đồng để phân biệt từng hợp đồng
    private String bookingID; // mã đặt phòng liên kết với hợp đồng
    private double depositAmmount; // số tiền khách đặt cọc trc
    private double totalPayment; // số tiền thanh toán tổng

    public Contract(int contractNum, String bookingID, double depositAmmount, double totalPayment) {
        this.contractNum = contractNum;
        this.bookingID = bookingID;
        this.depositAmmount = depositAmmount;
        this.totalPayment = totalPayment;
    }

    public int getContractNum() {
        return contractNum;
    }

    public void setContractNum(int contractNum) {
        this.contractNum = contractNum;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public double getDepositAmmount() {
        return depositAmmount;
    }

    public void setDepositAmmount(double depositAmmount) {
        this.depositAmmount = depositAmmount;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }
}
