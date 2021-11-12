package System.model;

public class Staff {

    String staffId;
    String staffPassword;
    String staffLevel;
    String staffName;
    String staffPhone;
    boolean isValid;

    public Staff() {
    }

    public Staff(String staffId, String staffPassword, String staffLevel, String staffName, String staffPhone, boolean isValid) {
        this.staffId = staffId;
        this.staffPassword = staffPassword;
        this.staffLevel = staffLevel;
        this.staffName = staffName;
        this.staffPhone = staffPhone;
        this.isValid = isValid;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public String getStaffLevel() {
        return staffLevel;
    }

    public void setStaffLevel(String staffLevel) {
        this.staffLevel = staffLevel;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
