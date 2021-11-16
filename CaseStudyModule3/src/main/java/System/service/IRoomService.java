package System.service;

import System.model.Room;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface IRoomService {

    ArrayList<Room> findRoomList();

    Room findRoomByRoomNumber(String roomNumber);

    void changeRoomStatus(String roomNumber, String status);

    void changeRoomAvailability(String roomNumber, boolean isAvailable);

    void changeRoomPrice(String roomNumber, BigDecimal price);

    void changeGuestId(String roomNumber, String guestId);

}
