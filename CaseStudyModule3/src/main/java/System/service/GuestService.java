package System.service;
import System.Tools.GetConnection;

import System.model.Guest;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class GuestService implements IGuestService{

    @Override
    public ArrayList<Guest> findBookingList() {
        ArrayList<Guest> bookingList = new ArrayList<>();
        String SELECT_BOOKING_LIST = "SELECT bookingCode, guestName, phoneNumber, dayArrival, dayLeave, guaranteeFee, methodPayment FROM Guests" +
                " WHERE bookingCode NOT IN (SELECT guestId FROM Rooms)";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKING_LIST);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String bookingCode = rs.getString("bookingCode");
                String guestName = rs.getString("guestName");
                String phoneNumber = rs.getString("phoneNumber");
                String dayArrival = rs.getString("dayArrival");
                String dayLeave = rs.getString("dayLeave");
                BigDecimal guaranteeFee = rs.getBigDecimal("guaranteeFee");
                String methodPayment = rs.getString("methodPayment");
                bookingList.add(new Guest(bookingCode,guestName,phoneNumber,dayArrival,dayLeave,guaranteeFee,methodPayment));
            }
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
        return bookingList;
    }

    @Override
    public ArrayList<Guest> findInHouseGuestList() {
        ArrayList<Guest> inHouseGuestList = new ArrayList<>();
        String SELECT_BOOKING_LIST = "SELECT bookingCode, guestName, phoneNumber, dayArrival, dayLeave, guaranteeFee, methodPayment FROM Guests" +
                " WHERE bookingCode IN (SELECT guestId FROM Rooms)";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKING_LIST);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String bookingCode = rs.getString("bookingCode");
                String guestName = rs.getString("guestName");
                String phoneNumber = rs.getString("phoneNumber");
                String dayArrival = rs.getString("dayArrival");
                String dayLeave = rs.getString("dayLeave");
                BigDecimal guaranteeFee = rs.getBigDecimal("guaranteeFee");
                String methodPayment = rs.getString("methodPayment");
                inHouseGuestList.add(new Guest(bookingCode,guestName,phoneNumber,dayArrival,dayLeave,guaranteeFee,methodPayment));
            }
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
        return inHouseGuestList;
    }

    @Override
    public Guest findGuestByBookingCode(String bookingCode) {
        Guest guest = null;
        String FIND_GUEST_IN_BOOKING_LIST_BY_ID = "SELECT bookingCode, guestName, phoneNumber, dayArrival, dayLeave, guaranteeFee, methodPayment FROM Guests" +
                " WHERE bookingCode = ?";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_GUEST_IN_BOOKING_LIST_BY_ID);
            preparedStatement.setString(1, bookingCode);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String guestName = rs.getString("guestName");
                String phoneNumber = rs.getString("phoneNumber");
                String dayArrival = rs.getString("dayArrival");
                String dayLeave = rs.getString("dayLeave");
                BigDecimal guaranteeFee = rs.getBigDecimal("guaranteeFee");
                String methodPayment = rs.getString("methodPayment");
                guest = new Guest(bookingCode,guestName,phoneNumber,dayArrival,dayLeave,guaranteeFee,methodPayment);
            }
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
        return guest;
    }

    @Override
    public ArrayList<Guest> findBookingCodeByName(String name) {
        ArrayList<Guest> guestList = new ArrayList<>();
        String FIND_BOOKING_LIST_BY_NAME = "SELECT * FROM" +
                " (SELECT bookingCode, guestName, phoneNumber, dayArrival, dayLeave, guaranteeFee, methodPayment FROM Guests" +
                " WHERE bookingCode NOT IN (SELECT guestId FROM Rooms)) AS BookingList " +
                "WHERE BookingList.guestName LIKE ?";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BOOKING_LIST_BY_NAME);
            String inPutGuestName = "%" + name + "%";
            System.out.println(inPutGuestName);
            preparedStatement.setString(1, inPutGuestName);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String bookingCode = rs.getString("bookingCode");
                String guestName = rs.getString("guestName");
                String phoneNumber = rs.getString("phoneNumber");
                String dayArrival = rs.getString("dayArrival");
                String dayLeave = rs.getString("dayLeave");
                BigDecimal guaranteeFee = rs.getBigDecimal("guaranteeFee");
                String methodPayment = rs.getString("methodPayment");
                Guest guest = new Guest(bookingCode,guestName,phoneNumber,dayArrival,dayLeave,guaranteeFee,methodPayment);
                guestList.add(guest);
            }
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
        return guestList;
    }

    @Override
    public Guest findGuestInHouseById(String guestId) {
        Guest guest = null;
        String FIND_GUEST_IN_BOOKING_LIST_BY_ID = "SELECT bookingCode, guestName, phoneNumber, dayArrival, dayLeave, guaranteeFee, methodPayment FROM Guests" +
                " WHERE bookingCode = ?";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_GUEST_IN_BOOKING_LIST_BY_ID);
            preparedStatement.setString(1, guestId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String guestName = rs.getString("guestName");
                String phoneNumber = rs.getString("phoneNumber");
                String dayArrival = rs.getString("dayArrival");
                String dayLeave = rs.getString("dayLeave");
                BigDecimal guaranteeFee = rs.getBigDecimal("guaranteeFee");
                String methodPayment = rs.getString("methodPayment");
                guest = new Guest(guestId,guestName,phoneNumber,dayArrival,dayLeave,guaranteeFee,methodPayment);
            }
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
        return guest;
    }

    @Override
    public boolean booking(Guest guest) throws SQLException {
        boolean isActionSuccess = true;
        String INSERT_GUEST_INTO_BOOKING = "INSERT INTO Guests (bookingCode, guestName, phoneNumber, dayArrival, dayLeave, guaranteeFee, methodPayment) VALUE (?, ?, ?, ?, ?, ?, ?);";
        Connection connection = GetConnection.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GUEST_INTO_BOOKING);
            preparedStatement.setString(1, guest.getBookingCode());
            preparedStatement.setString(2, guest.getGuestName());
            preparedStatement.setString(3, guest.getPhoneNumber());
            preparedStatement.setString(4, guest.getDayArrival());
            preparedStatement.setString(5, guest.getDayLeave());
            preparedStatement.setBigDecimal(6, guest.getGuaranteeFee());
            preparedStatement.setString(7, guest.getMethodPayment());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            isActionSuccess = false;
            GetConnection.printSQLException(e);
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return isActionSuccess;
    }

    @Override
    public boolean updateReservation(String bookingCode, Guest guest) throws SQLException {
        boolean isActionSuccess = true;
        String UPDATE_RESERVATION_INFORMATION = "UPDATE Guests SET guestName = ?, phoneNumber = ?, dayArrival = ?, dayLeave = ?, guaranteeFee = ?, methodPayment = ? WHERE bookingCode = ?;";
        Connection connection = GetConnection.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESERVATION_INFORMATION);
            preparedStatement.setString(1, guest.getGuestName());
            preparedStatement.setString(2, guest.getPhoneNumber());
            preparedStatement.setString(3, guest.getDayArrival());
            preparedStatement.setString(4, guest.getDayLeave());
            preparedStatement.setBigDecimal(5, guest.getGuaranteeFee());
            preparedStatement.setString(6, guest.getMethodPayment());
            preparedStatement.setString(7, guest.getBookingCode());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            isActionSuccess = false;
            connection.rollback();
            GetConnection.printSQLException(e);
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return isActionSuccess;
    }

    @Override
    public boolean removeReservation(String bookingCode) throws SQLException {
        boolean isActionSuccess = true;
        String DELETE_RESERVATION = "DELETE FROM Guests WHERE bookingCode = ?;";
        Connection connection = GetConnection.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATION);
            preparedStatement.setString(1, bookingCode);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            isActionSuccess = false;
            GetConnection.printSQLException(e);
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return isActionSuccess;
    }

    @Override
    public boolean checkIn(String bookingCode, String roomNumber) throws SQLException {
        boolean isActionSuccess = true;
        String CHECK_IN_GUEST_TO_ROOM = "UPDATE Rooms SET guestId = ?, roomStatus = ?, isAvailable = ?  WHERE roomNumber = ?;";
        Connection connection = GetConnection.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IN_GUEST_TO_ROOM);
            preparedStatement.setString(1, bookingCode);
            preparedStatement.setString(2, "OD");
            preparedStatement.setString(3, "false");
            preparedStatement.setString(4, roomNumber);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            isActionSuccess = false;
            GetConnection.printSQLException(e);
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return isActionSuccess;
    }

    @Override
    public boolean checkOut(String roomNumber, String bookingCode) throws SQLException {
        boolean isActionSuccess = true;
        String CHECK_OUT_GUEST_FROM_ROOM= "UPDATE Rooms SET guestId = ?, roomStatus = ?, isAvailable = ? WHERE roomNumber = ?";
        String CHECK_OUT_GUEST_FROM_GUESTS = "DELETE FROM Guests WHERE bookingCode = ?;";

        Connection connection = GetConnection.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_OUT_GUEST_FROM_ROOM);
            PreparedStatement preparedStatement2 = connection.prepareStatement(CHECK_OUT_GUEST_FROM_GUESTS);
            preparedStatement.setString(1, "empty");
            preparedStatement.setString(2, "VD");
            preparedStatement.setString(3, "true");
            preparedStatement.setString(4, roomNumber);
            preparedStatement2.setString(1, bookingCode);
            preparedStatement.executeUpdate();
            preparedStatement2.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            isActionSuccess = false;
            GetConnection.printSQLException(e);
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return isActionSuccess;
    }

}
