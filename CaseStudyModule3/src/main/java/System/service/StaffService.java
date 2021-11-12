package System.service;

import System.model.Staff;
import System.model.StaffHistory;

import java.sql.*;
import java.util.ArrayList;

public class StaffService implements IStaffService{

    private String jdbcURL = "jdbc:mysql://localhost:3306/FrontOfficeSystem?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    @Override
    public ArrayList<Staff> findStaffList() {
        ArrayList<Staff> staffList = new ArrayList<>();
        String SELECT_ROOM_LIST = "SELECT staffId, staffPassword, staffLevel, staffName, staffPhone, isValid FROM Staffs;";
        try {
            Connection connection = getConnection();
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
            printSQLException(e);
        }
        return staffList;
    }

    @Override
    public void changePassword(String staffId, String staffPassword) {
        String CHANGE_STAFF_PASSWORD = "UPDATE Staffs SET staffPassword = ? WHERE staffId =? ;";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_STAFF_PASSWORD);
            preparedStatement.setString(1, staffPassword);
            preparedStatement.setString(2, staffId);
            preparedStatement.execute();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void addHistory(String staffId, String action) {
        String ADD_STAFF_HISTORY = "INSERT INTO Staff_History (staffId, staffAction, Execution_date) VALUE (?, ?, DATE());";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_STAFF_HISTORY);
            preparedStatement.setString(1, staffId);
            preparedStatement.setString(2, action);
            preparedStatement.execute();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public ArrayList<StaffHistory> findHistory() {
        ArrayList<StaffHistory> staffHistories = new ArrayList<>();
        String FIND_STAFF_HISTORY = "SELECT staffId, staffAction, Execution_date FROM Staff_History";
        try {
            Connection connection = getConnection();
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
            printSQLException(e);
        }
        return staffHistories;
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
