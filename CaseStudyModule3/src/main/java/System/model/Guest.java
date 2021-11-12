package System.model;

import java.math.BigDecimal;

public class Guest {

    String bookingCode;
    String guestName;
    String phoneNumber;
    String dayArrival;
    String dayLeave;
    BigDecimal guaranteeFee;
    String methodPayment;

    public Guest() {
    }

    public Guest(String bookingCode, String guestName, String phoneNumber, String dayArrival, String dayLeave, BigDecimal guaranteeFee, String methodPayment) {
        this.bookingCode = bookingCode;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.dayArrival = dayArrival;
        this.dayLeave = dayLeave;
        this.guaranteeFee = guaranteeFee;
        this.methodPayment = methodPayment;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDayArrival() {
        return dayArrival;
    }

    public void setDayArrival(String dayArrival) {
        this.dayArrival = dayArrival;
    }

    public String getDayLeave() {
        return dayLeave;
    }

    public void setDayLeave(String dayLeave) {
        this.dayLeave = dayLeave;
    }

    public BigDecimal getGuaranteeFee() {
        return guaranteeFee;
    }

    public void setGuaranteeFee(BigDecimal guaranteeFee) {
        this.guaranteeFee = guaranteeFee;
    }

    public String getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(String methodPayment) {
        this.methodPayment = methodPayment;
    }
}
