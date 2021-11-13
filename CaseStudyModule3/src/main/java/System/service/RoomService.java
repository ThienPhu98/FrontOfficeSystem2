package System.service;
import System.Tools.GetConnection;

import System.model.Room;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class RoomService implements IRoomService{

    @Override
    public ArrayList<Room> findRoomList() {
        ArrayList<Room> roomList = new ArrayList<>();
        String SELECT_ROOM_LIST = "SELECT roomNumber, roomType, roomStatus, roomPrice, isAvailable, guestId FROM Rooms;";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROOM_LIST);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String roomNumber = rs.getString("roomNumber");
                String roomType = rs.getString("roomType");
                String roomStatus = rs.getString("roomStatus");
                BigDecimal roomPrice = rs.getBigDecimal("roomPrice");
                boolean isAvailable = false;
                String ouPutAvailable = rs.getString("isAvailable");
                if (ouPutAvailable.equals("true")) {
                    isAvailable = true;
                }
                String guestId = rs.getString("guestId");
                roomList.add(new Room(roomNumber, roomType, roomStatus, roomPrice, isAvailable, guestId));
            }
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
        return roomList;
    }

    @Override
    public void changeRoomStatus(String roomNumber, String status) {
        String CHANGE_ROOM_STATUS = "UPDATE Rooms SET roomStatus = ? WHERE roomNumber = ?;";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ROOM_STATUS);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, roomNumber);
            preparedStatement.execute();
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
    }

    @Override
    public void changeRoomAvailability(String roomNumber, boolean isAvailable) {
        String CHANGE_ROOM_AVAILABILITY = "UPDATE Rooms SET isAvailable = ? WHERE roomNumber = ?;";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ROOM_AVAILABILITY);
            if (isAvailable) {
                preparedStatement.setString(1, "true");
            } else {
                preparedStatement.setString(1, "false");
            }
            preparedStatement.setString(2, roomNumber);
            preparedStatement.execute();
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
    }

    @Override
    public void changeRoomPrice(String roomNumber, BigDecimal price) {
        String CHANGE_ROOM_PRICE = "UPDATE Rooms SET roomPrice = ? WHERE roomNumber = ?;";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ROOM_PRICE);
            preparedStatement.setBigDecimal(1, price);
            preparedStatement.setString(2, roomNumber);
            preparedStatement.execute();
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
    }

    @Override
    public void changeGuestId(String roomNumber, String guestId) {
        String CHANGE_ROOM_GUEST_ID = "UPDATE Rooms SET guestId = ? WHERE roomNumber = ?;";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ROOM_GUEST_ID);
            preparedStatement.setString(1, guestId);
            preparedStatement.setString(2, roomNumber);
            preparedStatement.execute();
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
    }
}
