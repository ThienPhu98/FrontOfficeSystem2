package System.controller;

import System.Tools.CompareDate;
import System.Tools.DateValidatorUsingDateFormat;
import System.model.Guest;
import System.model.Room;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "SystemServlet", urlPatterns = "/system")
public class SystemServlet extends HttpServlet {

    private IGuestService guestService = new GuestService();
    private IRoomService roomService = new RoomService();
    private IStaffService staffService = new StaffService();
    CompareDate compareDate = new CompareDate();
    DateValidatorUsingDateFormat dateFormat = new DateValidatorUsingDateFormat();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "booking":
                try {
                    doBooking(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "bookingList":
                showBookingList(request, response);
                break;
            case "updateReservation":
                try {
                    doUpdateReservation(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "removeReservation":
                try {
                    doRemove(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "checkInWithReservation":
                doCheckInByReservation(request, response);
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
                showRemoveConfirm(request, response);
                break;
            case "checkInWithReservation":
                showCheckInConfirm(request, response);
                break;
            case "checkInWithOutReservation":
//                showRegistrationForm(request, response);
                break;
            case "checkOut":
                break;
            case "showRoomList":
                showRoomList(request, response);
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
        String today = String.valueOf(java.time.LocalDate.now());
        ArrayList<Guest> bookingList = guestService.findBookingList();
        ArrayList<Guest> inHouseList = guestService.findInHouseGuestList();

        int countArrivalGuest = 0;
        for (Guest guest: bookingList) {
            if (guest.getDayArrival().equals(today)) {
                countArrivalGuest += 1;
            }
        }

        int countDepartureGuest = 0;
        for (Guest guest: inHouseList){
            if (guest.getDayLeave().equals(today)) {
                countDepartureGuest += 1;
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("staff/mainMenu.jsp");
        request.setAttribute("countArrivalGuest", countArrivalGuest);
        request.setAttribute("countDepartureGuest", countDepartureGuest);
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

    private void doBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String checkName;
        String fullName = "";
        String OutPutFullName = request.getParameter("guestName");
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

        String today = String.valueOf(java.time.LocalDate.now());
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
            if (compareDate.compare(departureDate,arrivalDate) > 0) {
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("guest/booking.jsp");
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
                    if (guestService.booking(guest)) {
                        request.setAttribute("message", "success");
                    } else {
                        request.setAttribute("message", "false");
                    }
                }
            }
        } else {
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
        String bookingCode = request.getParameter("bookingCode");
        String guestName = request.getParameter("guestName");
        RequestDispatcher dispatcher = request.getRequestDispatcher("guest/bookingList.jsp");
        ArrayList<Guest> bookingList = new ArrayList<>();

        if (bookingCode == null || bookingCode.equals("")) {
            if (guestName == null || guestName.equals("")) {
                bookingList = guestService.findBookingList();
            } else {
                bookingList = guestService.findBookingCodeByName(guestName);
            }
        } else {
            Guest guest = guestService.findGuestByBookingCode(bookingCode);
            bookingList.add(guest);
        }
        request.setAttribute("bookingList", bookingList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) {
        String bookingCode = request.getParameter("id");
        Guest guest = guestService.findGuestByBookingCode(bookingCode);
        RequestDispatcher dispatcher;
        if(guest == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("guest", guest);
            dispatcher = request.getRequestDispatcher("guest/update.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void doUpdateReservation(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String checkName;
        String fullName = "";
        String OutPutFullName = request.getParameter("guestName");
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

        String today = String.valueOf(java.time.LocalDate.now());
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
            if (compareDate.compare(departureDate,arrivalDate) > 0) {
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

        String methodPayment = request.getParameter("methodPayment");
        String bookingCode = request.getParameter("bookingCode");

        RequestDispatcher dispatcher = null;
        if (checkName.equals("success") && checkPhoneNumber.equals("success") && checkArrivalDate.equals("success")
                && checkDepartureDate.equals("success") && checkGuaranteeFee.equals("success")) {
            Guest guest = new Guest(bookingCode, fullName, phoneNumber, arrivalDate, departureDate, guaranteeFee, methodPayment);
            if (guestService.updateReservation(bookingCode,guest)) {
                ArrayList<Guest> bookingList = guestService.findBookingList();
                request.setAttribute("bookingList", bookingList);
                request.setAttribute("message", "update");
                dispatcher = request.getRequestDispatcher("guest/bookingList.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("guest/update.jsp");
                request.setAttribute("message", "false");
            }
        } else {
            dispatcher = request.getRequestDispatcher("guest/update.jsp");
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
            request.setAttribute("methodPayment", methodPayment);
            request.setAttribute("bookingCode", bookingCode);
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showRemoveConfirm(HttpServletRequest request, HttpServletResponse response){
        String bookingCode = request.getParameter("id");
        Guest guest = guestService.findGuestByBookingCode(bookingCode);
        RequestDispatcher dispatcher;
        if(guest == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("guest", guest);
            dispatcher = request.getRequestDispatcher("guest/remove.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void doRemove(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String bookingCode = request.getParameter("bookingCode");
        RequestDispatcher dispatcher;
        if (guestService.removeReservation(bookingCode)) {
            ArrayList<Guest> bookingList = guestService.findBookingList();
            request.setAttribute("bookingList", bookingList);
            request.setAttribute("message", "remove");
            dispatcher = request.getRequestDispatcher("guest/bookingList.jsp");
        } else {
            Guest guest = guestService.findGuestByBookingCode(bookingCode);
            request.setAttribute("guest", guest);
            dispatcher = request.getRequestDispatcher("guest/remove.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCheckInConfirm(HttpServletRequest request, HttpServletResponse response) {
        String bookingCode = request.getParameter("bookingCode");
        Guest guest = guestService.findGuestByBookingCode(bookingCode);
        ArrayList<Room> roomList = roomService.findRoomList();
        RequestDispatcher dispatcher;
        if(guest == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("guest", guest);
            request.setAttribute("roomList", roomList);
            dispatcher = request.getRequestDispatcher("guest/checkIn.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void doCheckInByReservation(HttpServletRequest request, HttpServletResponse response) {
        String bookingCode = request.getParameter("bookingCode");
        String roomNumber = request.getParameter("roomNumber");

    }

    private void showRegistrationForm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void doCheckInByRegistration(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showCheckOutConfirm(HttpServletRequest request, HttpServletResponse response) {

    }

    private void doCheckOut(HttpServletRequest request, HttpServletResponse response) {

    }

    private void showRoomList(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("room/roomList.jsp");
        ArrayList<Room> roomList = roomService.findRoomList();
        request.setAttribute("roomList", roomList);
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
        StringBuilder stringBuffer = new StringBuilder();

        for (String s : arr) {
            stringBuffer.append(Character.toUpperCase(s.charAt(0)))
                    .append(s.substring(1)).append(" ");
        }
        return stringBuffer.toString().trim();
    }
}
