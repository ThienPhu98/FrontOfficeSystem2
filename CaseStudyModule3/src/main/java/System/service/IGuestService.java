package System.service;

import System.model.Guest;
import System.model.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IGuestService {

    ArrayList<Guest> findBookingList();

    ArrayList<Guest> findInHouseGuestList();

    Guest findGuestByBookingCode(String bookingCode);

    Guest findGuestInHouseById(String guestId);

//do booking
    boolean booking(Guest guest) throws SQLException;

    boolean updateReservation(String bookingCode, Guest guest) throws SQLException;

    boolean removeReservation(String bookingCode) throws SQLException;


//do check-in
    void checkIn(Guest guest, String roomNumber);


//do check-out
    void checkOut(String roomNumber);


}
