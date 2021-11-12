package System.service;

import System.model.Guest;
import System.model.Room;

import java.util.ArrayList;

public interface IGuestService {

    ArrayList<Guest> findBookingList();

    ArrayList<Guest> findInHouseGuestList();

    Guest findGuestByBookingCode(String bookingCode);

    Guest findGuestInHouseById(String guestId);

//do booking
    void booking(Guest guest);

    void updateReservation(String bookingCode, Guest guest);

    void removeReservation(String bookingCode);


//do check-in
    void checkIn(Guest guest, String roomNumber);


//do check-out
    void checkOut(String roomNumber);


}
