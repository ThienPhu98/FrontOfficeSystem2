package System.service;

import System.model.Staff;
import System.model.StaffHistory;

import java.util.ArrayList;

public interface IStaffService {

    ArrayList<Staff> findStaffList();

    void changePassword(String staffId, String staffPassword);

    void addHistory(String staffId, String action);

    ArrayList<StaffHistory> findHistory();
}
