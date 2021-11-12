package System.model;

public class StaffHistory {
    String staffId;
    String action;
    String executionDate;

    public StaffHistory(){
    }

    public StaffHistory(String staffId, String action, String executionDate) {
        this.staffId = staffId;
        this.action = action;
        this.executionDate = executionDate;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }
}
