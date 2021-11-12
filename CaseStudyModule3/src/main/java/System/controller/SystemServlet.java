package System.controller;

import System.Tools.CompareDate;
import System.Tools.DateTransfer;
import System.Tools.DateValidatorUsingDateFormat;
import System.model.Guest;
import System.model.Staff;
import System.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "SystemServlet", urlPatterns = "/system")
public class SystemServlet extends HttpServlet {

    private IGuestService guestService = new GuestService();
    private IRoomService roomService = new RoomService();
    private IStaffService staffService = new StaffService();
    CompareDate compareDate = new CompareDate();
    DateTransfer dateTransfer = new DateTransfer();
    DateValidatorUsingDateFormat dateFormat = new DateValidatorUsingDateFormat();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "booking":
                doBooking(request, response);
                break;
            case "updateReservation":
                doUpdateReservation(request, response);
                break;
            case "checkInWithOutReservation":
                break;
            case "checkOut":
                break;
            default:
                doSignIn(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "mainMenu":
                showMainMenu(request, response);
                break;
            case "booking":
                showBookingForm(request, response);
                break;
            case "bookingList":
                showBookingList(request, response);
                break;
            case "updateReservation":
                showUpdateForm(request, response);
                break;
            case "removeReservation":
//                showRemoveForm(request, response);
                break;
            case "checkInWithReservation":
//                showConfirmCheckIn(request, response);
                break;
            case "checkInWithOutReservation":
//                showRegistrationForm(request, response);
                break;
            case "checkOut":
                break;
            default:
                showSignIn(request, response, null);
                break;
        }
    }

    private void showSignIn(HttpServletRequest request, HttpServletResponse response, String message) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("staff/signIn.jsp");
        request.setAttribute("message", message);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void doSignIn(HttpServletRequest request, HttpServletResponse response) {

        ArrayList<Staff> staffList = staffService.findStaffList();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        boolean isSignIn = false;
        for (Staff staff : staffList) {
            if (staff.getStaffId().equals(userName) && staff.getStaffPassword().equals(password)) {
                isSignIn = true;
                break;
            }
        }

        if (isSignIn) {
            showMainMenu(request, response);
        } else {
            String message = "false";
            showSignIn(request, response, message);
        }
    }

    private void showMainMenu(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher("staff/mainMenu.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showBookingForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("guest/booking.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void doBooking(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = null;

        String checkName;
        String fullName = "";
        String OutPutFullName = request.getParameter("fullName");
        if (OutPutFullName.length() > 0) {
            Pattern pattern = Pattern.compile("^([A-Za-z]*[ ]?)+$");
            Matcher matcher = pattern.matcher(OutPutFullName);
            if (matcher.matches()) {
                checkName = "success";
                fullName = toTitleCase(OutPutFullName);
            } else {
                checkName = "false";
            }
        } else {
            checkName = "false";
        }

        String checkPhoneNumber;
        String phoneNumber = request.getParameter("phoneNumber");
        if(phoneNumber.length() == 10 || phoneNumber.length() == 11) {
            Pattern pattern = Pattern.compile("^[0]\\d{9,10}");
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches()) {
                checkPhoneNumber = "success";
            } else {
                checkPhoneNumber = "false";
            }
        } else {
            checkPhoneNumber = "false";
        }

        String outPutToday = String.valueOf(java.time.LocalDate.now());
        String today = dateTransfer.transfer(outPutToday);
        String checkArrivalDate;
        String arrivalDate = request.getParameter("arrivalDate");
        if (dateFormat.isDateValid(arrivalDate)) {
            if (compareDate.compare(arrivalDate,today) >= 0) {
                checkArrivalDate = "success";
            } else {
                checkArrivalDate = "false";
            }
        } else {
            checkArrivalDate = "false";
        }

        String checkDepartureDate;
        String departureDate = request.getParameter("departureDate");
        if (dateFormat.isDateValid(departureDate)) {
            if (compareDate.compare(departureDate,arrivalDate) >= 0) {
                checkDepartureDate = "success";
            } else {
                checkDepartureDate = "false";
            }
        } else {
            checkDepartureDate = "false";
        }

        String checkGuaranteeFee;
        String outPutGuaranteeFee = request.getParameter("guaranteeFee");
        BigDecimal guaranteeFee = null;
        if (outPutGuaranteeFee.length() <= 10) {
            Pattern pattern = Pattern.compile("\\d*");
            Matcher matcher = pattern.matcher(outPutGuaranteeFee);
            if (matcher.matches()) {
                guaranteeFee = new BigDecimal(outPutGuaranteeFee);
                checkGuaranteeFee = "success";
            } else {
                checkGuaranteeFee = "false";
            }
        } else {
            checkGuaranteeFee = "false";
        }

        if (checkName.equals("success") && checkPhoneNumber.equals("success") && checkArrivalDate.equals("success")
                && checkDepartureDate.equals("success") && checkGuaranteeFee.equals("success")) {
            String methodPayment = request.getParameter("methodPayment");

            boolean checkBookingCode = false;
            while (!checkBookingCode) {
                int randomNumber = (int) (Math.random() * 99999) + 10000;
                String bookingCode = String.valueOf(randomNumber);
                if(isBookingCodeValid(bookingCode)) {
                    checkBookingCode = true;
                    Guest guest = new Guest(bookingCode, fullName, phoneNumber, arrivalDate, departureDate, guaranteeFee, methodPayment);
                    guestService.booking(guest);
                    dispatcher = request.getRequestDispatcher("guest/booking.jsp");
                    request.setAttribute("message", "success");
                }
            }
        } else {
            dispatcher = request.getRequestDispatcher("guest/booking.jsp");
            request.setAttribute("message", "false");
            request.setAttribute("checkName", checkName);
            request.setAttribute("checkPhoneNumber", checkPhoneNumber);
            request.setAttribute("checkArrivalDate", checkArrivalDate);
            request.setAttribute("checkDepartureDate", checkDepartureDate);
            request.setAttribute("checkGuaranteeFee", checkGuaranteeFee);

            request.setAttribute("fullName", fullName);
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("arrivalDate", arrivalDate);
            request.setAttribute("departureDate", departureDate);
            request.setAttribute("outPutGuaranteeFee", outPutGuaranteeFee);
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void showBookingList(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Guest> bookingList = guestService.findBookingList();
        RequestDispatcher dispatcher = request.getRequestDispatcher("guest/bookingList.jsp");
        request.setAttribute("bookingList", bookingList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) {
        String bookingCode = request.getParameter("bookingCode");
        Guest guest = guestService.findGuestByBookingCode(bookingCode);
        RequestDispatcher dispatcher;
        if(guest == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("guest", guest);
            dispatcher = request.getRequestDispatcher("guest/updateReservation.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void doUpdateReservation (HttpServletRequest request, HttpServletResponse response) {
        String bookingCode = request.getParameter("bookingCode");
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String dayArrival = request.getParameter("arrivalDate");
        String departureDate = request.getParameter("departureDate");
        String outPutGuaranteeFee = request.getParameter("guaranteeFee");
        BigDecimal guaranteeFee = new BigDecimal(outPutGuaranteeFee);
        String methodPayment = request.getParameter("methodPayment");

        Guest guest = new Guest(bookingCode, fullName, phoneNumber, dayArrival, departureDate, guaranteeFee, methodPayment);
        guestService.updateReservation(bookingCode, guest);

        RequestDispatcher dispatcher = request.getRequestDispatcher("guest/updateReservation.jsp");
        request.setAttribute("message", "Success");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }




    public boolean isBookingCodeValid(String bookingCode) {
        ArrayList<Guest> bookingList = guestService.findBookingList();
        ArrayList<Guest> inHouseList = guestService.findInHouseGuestList();

        for (Guest guest : bookingList) {
            if(guest.getBookingCode().equals(bookingCode)) {
                return false;
            }
        }

        for (Guest guest : inHouseList) {
            if(guest.getBookingCode().equals(bookingCode)) {
                return false;
            }
        }

        return true;
    }

    public String toTitleCase(String inputString) {
        String[] arr = inputString.split(" ");
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            stringBuffer.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        return stringBuffer.toString().trim();
    }
}
