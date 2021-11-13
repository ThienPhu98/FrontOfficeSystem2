package System.Tools;

public class CompareDate {
    public int compare(String date1, String date2){
        String[] FirstDate = date1.split("-");
        String[] SecondDate = date2.split("-");
        int dayDate1 = Integer.parseInt(FirstDate[2]);
        int monthDate1 = Integer.parseInt(FirstDate[1]);
        int yearDate1 = Integer.parseInt(FirstDate[0]);

        int dayDate2 = Integer.parseInt(SecondDate[2]);
        int monthDate2 = Integer.parseInt(SecondDate[1]);
        int yearDate2 = Integer.parseInt(SecondDate[0]);

        if (compareInt(yearDate1, yearDate2) != 0) {
            return compareInt(yearDate1, yearDate2);
        } else if (compareInt(monthDate1, monthDate2) != 0) {
            return compareInt(monthDate1, monthDate2);
        } else {
            return compareInt(dayDate1, dayDate2);
        }
    }
    private  int compareInt(int num1, int num2){
        if (num1 > num2){
            return 1;
        } else if (num1 == num2){
            return 0;
        } else {
            return -1;
        }
    }
}
