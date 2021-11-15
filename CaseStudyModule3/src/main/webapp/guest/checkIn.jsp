<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Check In</title>
    <%@include file ="/layout/headAndLink.jsp" %>
    <style>
        .active_point:hover {
            cursor: pointer;
        }
    </style>
</head>
<body>

<div id="wrapper">
    <%@include file ="/layout/navbar.jsp" %>

    <%@include file ="/layout/left-side-menu.jsp" %>

    <div class="content-page">
        <div class="content">
            <div class="row">
                <div class="col-12">
                    <div class="page-title-box">
                        <div class="page-title-right">
                            <ol class="breadcrumb m-0">
                                <li class="breadcrumb-item" onclick='window.location.href="/system?action=mainMenu"'>
                                    <i class="fas fa-user-alt"></i>
                                    <span class= "active_point">MainMenu</span>
                                </li>
                                <li class="breadcrumb-item" onclick='window.location.href="/system?action=bookingList"'>
                                    <i class="fas fa-list-alt"></i>
                                    <span class= "active_point">BookingList</span>
                                </li>
                                <li class="breadcrumb-item active">
                                    <i class="fas fa-user-check"></i>
                                    <span>Check In</span>
                                </li>
                            </ol>
                        </div>
                        <h4 class="page-title">Check In</h4>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <form class="row g-3" method="post">
                                <div class="col-md-12">
                                    <label for="validationDefault00" class="form-label">Booking Code</label>
                                    <input type="text" class="form-control" id="validationDefault00" value=${guest.getBookingCode()} name="bookingCode" readonly>
                                </div>

                                <div class="col-md-6">
                                    <label for="validationDefault01" class="form-label">Guest name</label>
                                    <input type="text" class="form-control" id="validationDefault01" value=${guest.getGuestName()} name="guestName" readonly>
                                </div>
                                <div class="col-md-6">
                                    <label for="validationDefault02" class="form-label">Guest Phone Number</label>
                                    <input type="number" class="form-control" id="validationDefault02" value=${guest.getPhoneNumber()} name="phoneNumber" readonly>
                                </div>

                                <div class="col-md-6">
                                    <label for="validationDefault03" class="form-label">Arrive Date</label>
                                    <input type="date" class="form-control" id="validationDefault03" value=${guest.getDayArrival()} name="arrivalDate" readonly>
                                </div>
                                <div class="col-md-6">
                                    <label for="validationDefault04" class="form-label">Departure Date</label>
                                    <input type="date" class="form-control" id="validationDefault04" value=${guest.getDayLeave()} name="departureDate" readonly>
                                </div>

                                <div class="col-md-9">
                                    <label for="validationDefaultUsername" class="form-label">Guarantee Fee</label>
                                    <div class="input-group">
                                        <input type="number" class="form-control" id="validationDefaultUsername" value=${guest.getGuaranteeFee()} name="guaranteeFee" style="text-align: right" readonly>
                                        <span class="input-group-text" id="inputGroupPrepend3">VND</span>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <label for="validationDefault05" class="form-label">Method Payment</label>
                                    <input type="date" class="form-control" id="validationDefault05" value=${guest.getMethodPayment()} name="methodPayment" readonly>
                                </div>

                                <div class="col-6">
                                    <button class="btn btn-primary" type="submit">Confirm Check-In</button>
                                </div>

                                <div class="col-6">
                                    <span class="input-group-text">Room Number</span>
                                    <input type="text" class="form-control" placeholder="Enter Room Number" aria-label="Username" aria-describedby="addon-wrapping" name="roomNumber" required>
                                </div>
                            </form>
                        </div>

                        <div class="card-body">
                            <table class="table table-hover table-centered mb-0">
                                <thead>
                                <tr class="table-primary">
                                    <th scope="col">#</th>
                                    <th scope="col">roomType</th>
                                    <th scope="col">roomStatus</th>
                                    <th scope="col">roomPrice</th>
                                    <th scope="col">guestId</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items='${requestScope["roomList"]}' var="room">
                                    <tr>
                                        <td>${room.getRoomNumber()}</td>
                                        <td>${room.roomType()}</td>
                                        <td>${room.roomStatus()}</td>
                                        <td>${room.roomPrice()}</td>
                                        <td>${room.guestId()}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <%@include file ="/layout/footer.jsp" %>
        </div>
    </div>
</div>

<%@include file ="/layout/script.jsp" %>
</body>
</html>

