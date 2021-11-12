package System.Tools;

public class DateTransfer {
    public String transfer(String date) {
        String[] dateSplit = date.split("-");
        return dateSplit[1] + "-" + dateSplit[2] + "-" + dateSplit[0];
    }
}
