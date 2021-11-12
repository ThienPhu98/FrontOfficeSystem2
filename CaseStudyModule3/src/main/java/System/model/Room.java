package System.model;

import java.math.BigDecimal;

public class Room {

    String roomNumber;
    String roomType;
    String roomStatus;
    BigDecimal roomPrice;
    Boolean isAvailable;
    String guestId;

    public Room() {
    }

    public Room(String roomNumber, String roomType, String roomStatus, BigDecimal roomPrice, Boolean isAvailable, String guestId) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomStatus = roomStatus;
        this.roomPrice = roomPrice;
        this.isAvailable = isAvailable;
        this.guestId = guestId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }
}
