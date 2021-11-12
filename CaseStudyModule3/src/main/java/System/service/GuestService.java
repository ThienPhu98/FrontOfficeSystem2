package System.service;

import System.Tools.CompareDate;
import System.Tools.DateTransfer;
import System.Tools.DateValidatorUsingDateFormat;
import System.model.Guest;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class GuestService implements IGuestService{

    private String jdbcURL = "jdbc:mysql://localhost:3306/FrontOfficeSystem?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    DateTransfer dateTransfer = new DateTransfer();

    @Override
    public ArrayList<Guest> findBookingList() {
        ArrayList<Guest> bookingList = new ArrayList<>();
        String SELECT_BOOKING_LIST = "SELECT bookingCode, guestName, phoneNumber, dayArrival, dayLeave, guaranteeFee, methodPayment FROM Guests" +
                " WHERE bookingCode NOT IN (SELECT guestId FROM Rooms)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKING_LIST);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String bookingCode = rs.getString("bookingCode");
                String guestName = rs.getString("guestName");
                String phoneNumber = rs.getString("phoneNumber");
                String outPutDayArrival = rs.getString("dayArrival");
                String outPutDayLeave = rs.getString("dayLeave");
                String dayArrival = dateTransfer.transfer(outPutDayArrival);
                String dayLeave = dateTransfer.transfer(outPutDayLeave);
                BigDecimal guaranteeFee = rs.getBigDecimal("guaranteeFee");
                String methodPayment = rs.getString("methodPayment");
                bookingList.add(new Guest(bookingCode,guestName,phoneNumber,dayArrival,dayLeave,guaranteeFee,methodPayment));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return bookingList;
    }

    @Override
    public ArrayList<Guest> findInHouseGuestList() {
        ArrayList<Guest> inHouseGuestList = new ArrayList<>();
        String SELECT_BOOKING_LIST = "SELECT bookingCode, guestName, phoneNumber, dayArrival, dayLeave, guaranteeFee, methodPayment FROM Guests" +
                " WHERE bookingCode IN (SELECT guestId FROM Rooms)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKING_LIST);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String bookingCode = rs.getString("bookingCode");
                String guestName = rs.getString("guestName");
                String phoneNumber = rs.getString("phoneNumber");
                String outPutDayArrival = rs.getString("dayArrival");
                String outPutDayLeave = rs.getString("dayLeave");
                String dayArrival = dateTransfer.transfer(outPutDayArrival);
                String dayLeave = dateTransfer.transfer(outPutDayLeave);
                BigDecimal guaranteeFee = rs.getBigDecimal("guaranteeFee");
                String methodPayment = rs.getString("methodPayment");
                inHouseGuestList.add(new Guest(bookingCode,guestName,phoneNumber,dayArrival,dayLeave,guaranteeFee,methodPayment));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return inHouseGuestList;
    }

    @Override
    public Guest findGuestByBookingCode(String bookingCode) {
        Guest guest = null;
        String FIND_GUEST_IN_BOOKING_LIST_BY_ID = "SELECT bookingCode, guestName, phoneNumber, dayArrival, dayLeave, guaranteeFee, methodPayment FROM Guests" +
                " WHERE (bookingCode NOT IN (SELECT guestId FROM Rooms)) AND (bookingCode = ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_GUEST_IN_BOOKING_LIST_BY_ID);
            preparedStatement.setString(1, bookingCode);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String guestName = rs.getString("guestName");
                String phoneNumber = rs.getString("phoneNumber");
                String outPutDayArrival = rs.getString("dayArrival");
                String outPutDayLeave = rs.getString("dayLeave");
                String dayArrival = dateTransfer.transfer(outPutDayArrival);
                String dayLeave = dateTransfer.transfer(outPutDayLeave);
                BigDecimal guaranteeFee = rs.getBigDecimal("guaranteeFee");
                String methodPayment = rs.getString("methodPayment");
                guest = new Guest(bookingCode,guestName,phoneNumber,dayArrival,dayLeave,guaranteeFee,methodPayment);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return guest;
    }

    @Override
    public Guest findGuestInHouseById(String guestId) {
        Guest guest = null;
        String FIND_GUEST_IN_BOOKING_LIST_BY_ID = "SELECT bookingCode, guestName, phoneNumber, dayArrival, dayLeave, guaranteeFee, methodPayment FROM Guests" +
                " WHERE (bookingCode IN (SELECT guestId FROM Rooms)) AND (bookingCode = ?)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_GUEST_IN_BOOKING_LIST_BY_ID);
            preparedStatement.setString(1, guestId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String guestName = rs.getString("guestName");
                String phoneNumber = rs.getString("phoneNumber");
                String outPutDayArrival = rs.getString("dayArrival");
                String outPutDayLeave = rs.getString("dayLeave");
                String dayArrival = dateTransfer.transfer(outPutDayArrival);
                String dayLeave = dateTransfer.transfer(outPutDayLeave);
                BigDecimal guaranteeFee = rs.getBigDecimal("guaranteeFee");
                String methodPayment = rs.getString("methodPayment");
                guest = new Guest(guestId,guestName,phoneNumber,dayArrival,dayLeave,guaranteeFee,methodPayment);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return guest;
    }

    @Override
    public void booking(Guest guest) {
        String INSERT_GUEST_INTO_BOOKING = "INSERT INTO Guests (bookingCode, guestName, phoneNumber, dayArrival, dayLeave, guaranteeFee, methodPayment) VALUE (?, ?, ?, ?, ?, ?, ?);";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GUEST_INTO_BOOKING);
            preparedStatement.setString(1, guest.getBookingCode());
            preparedStatement.setString(2, guest.getGuestName());
            preparedStatement.setString(3, guest.getPhoneNumber());
            String dayArrival = dateTransfer.transfer(guest.getDayArrival());
            String dayLeave = dateTransfer.transfer(guest.getDayLeave());
            preparedStatement.setString(4, dayArrival);
            preparedStatement.setString(5, dayLeave);
            preparedStatement.setBigDecimal(6, guest.getGuaranteeFee());
            preparedStatement.setString(7, guest.getMethodPayment());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void updateReservation(String bookingCode, Guest guest) {
        String UPDATE_RESERVATION_INFORMATION = "UPDATE Guests SET (guestName = ?, phoneNumber = ?, dayArrival = ?, dayLeave = ?, guaranteeFee = ?, methodPayment = ?) WHERE bookingCode = ?;";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESERVATION_INFORMATION);
            preparedStatement.setString(1, guest.getGuestName());
            preparedStatement.setString(2, guest.getPhoneNumber());
            String dayArrival = dateTransfer.transfer(guest.getDayArrival());
            String dayLeave = dateTransfer.transfer(guest.getDayLeave());
            preparedStatement.setString(3, dayArrival);
            preparedStatement.setString(4, dayLeave);
            preparedStatement.setBigDecimal(5, guest.getGuaranteeFee());
            preparedStatement.setString(6, guest.getMethodPayment());
            preparedStatement.setString(7, guest.getBookingCode());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void removeReservation(String bookingCode) {
        String DELETE_RESERVATION = "DELETE FROM Guests WHERE bookingCode = ?;";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATION);
            preparedStatement.setString(1, bookingCode);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void checkIn(Guest guest, String roomNumber) {

    }

    @Override
    public void checkOut(String roomNumber) {

    }


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
