package System.service;
import System.Tools.GetConnection;

import System.model.Staff;
import System.model.StaffHistory;

import java.sql.*;
import java.util.ArrayList;

public class StaffService implements IStaffService{

    @Override
    public ArrayList<Staff> findStaffList() {
        ArrayList<Staff> staffList = new ArrayList<>();
        String SELECT_ROOM_LIST = "SELECT staffId, staffPassword, staffLevel, staffName, staffPhone, isValid FROM Staffs;";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROOM_LIST);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String staffId = rs.getString("staffId");
                String staffPassword = rs.getString("staffPassword");
                String staffLevel = rs.getString("staffLevel");
                String staffName = rs.getString("staffName");
                String staffPhone = rs.getString("staffPhone");
                String ouPutIsValid = rs.getString("isValid");
                boolean isValid = false;
                if(ouPutIsValid.equals("true")) {
                    isValid = true;
                }
                staffList.add(new Staff(staffId, staffPassword, staffLevel, staffName, staffPhone, isValid));
            }
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
        return staffList;
    }

    @Override
    public void changePassword(String staffId, String staffPassword) {
        String CHANGE_STAFF_PASSWORD = "UPDATE Staffs SET staffPassword = ? WHERE staffId =? ;";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_STAFF_PASSWORD);
            preparedStatement.setString(1, staffPassword);
            preparedStatement.setString(2, staffId);
            preparedStatement.execute();
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
    }

    @Override
    public void addHistory(String staffId, String action) {
        String ADD_STAFF_HISTORY = "INSERT INTO Staff_History (staffId, staffAction, Execution_date) VALUE (?, ?, DATE());";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_STAFF_HISTORY);
            preparedStatement.setString(1, staffId);
            preparedStatement.setString(2, action);
            preparedStatement.execute();
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
    }

    @Override
    public ArrayList<StaffHistory> findHistory() {
        ArrayList<StaffHistory> staffHistories = new ArrayList<>();
        String FIND_STAFF_HISTORY = "SELECT staffId, staffAction, Execution_date FROM Staff_History";
        try {
            Connection connection = GetConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_STAFF_HISTORY);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String staffId = rs.getString("staffId");
                String staffAction = rs.getString("staffAction");
                String executionDate = rs.getString("Execution_date");
                staffHistories.add(new StaffHistory(staffId, staffAction, executionDate));
            }
        } catch (SQLException e) {
            GetConnection.printSQLException(e);
        }
        return staffHistories;
    }
}
