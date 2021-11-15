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

    ArrayList<Guest> findBookingCodeByName(String name);

//do booking
    boolean booking(Guest guest) throws SQLException;

    boolean updateReservation(String bookingCode, Guest guest) throws SQLException;

    boolean removeReservation(String bookingCode) throws SQLException;


//do check-in
    boolean checkIn(String bookingCode, String roomNumber) throws SQLException;


//do check-out
    boolean checkOut(String roomNumber, String bookingCode) throws SQLException;


}
