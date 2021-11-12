<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Booking List</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div>--%>
<%--    <h1>Booking List</h1>--%>
<%--    <div>--%>
<%--        <table class="table table-hover table-centered mb-0">--%>
<%--            <thead>--%>
<%--            <tr class="table-primary">--%>
<%--                <th scope="col">#</th>--%>
<%--                <th scope="col">guestName</th>--%>
<%--                <th scope="col">phoneNumber</th>--%>
<%--                <th scope="col">dayArrival</th>--%>
<%--                <th scope="col">dayLeave</th>--%>
<%--                <th scope="col">guaranteeFee</th>--%>
<%--                <th scope="col">methodPayment</th>--%>
<%--                <th colspan="3">Action</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <c:forEach items='${requestScope["bookingList"]}' var="guest">--%>
<%--                <tr>--%>
<%--                    <td>${guest.getBookingCode()}</td>--%>
<%--                    <td>${guest.getGuestName()}</td>--%>
<%--                    <td>${guest.getPhoneNumber()}</td>--%>
<%--                    <td>${guest.getDayArrival()}</td>--%>
<%--                    <td>${guest.getDayLeave()}</td>--%>
<%--                    <td>${guest.getGuaranteeFee()}</td>--%>
<%--                    <td>${guest.getMethodPayment()}</td>--%>
<%--                    <td>--%>
<%--                        <button type="button" class="btn btn-outline-success" onclick='window.location.href="/system?action=checkIn&bookingCode=${guest.getBookingCode()}"'>--%>
<%--                        </button>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <button type="button" class="btn btn-outline-success" onclick='window.location.href="/system?action=updateBookingList&bookingCode=${guest.getBookingCode()}"'>--%>
<%--                        </button>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <button type="button" class="btn btn-outline-success" onclick='window.location.href="/system?action=removeBookingList&bookingCode=${guest.getBookingCode()}"'>--%>
<%--                        </button>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Booking List</title>
    <%@include file ="/layout/headAndLink.jsp" %>
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
                                    <span>MainMenu</span>
                                </li>
                                <li class="breadcrumb-item active">
                                    <i class="fas fa-user-plus"></i>
                                    <span>Booking List</span>
                                </li>
                            </ol>
                        </div>
                        <h4 class="page-title">Booking List</h4>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <table class="table table-hover table-centered mb-0">
                                <thead>
                                <tr class="table-primary">
                                    <th scope="col">#</th>
                                    <th scope="col">guestName</th>
                                    <th scope="col">phoneNumber</th>
                                    <th scope="col">dayArrival</th>
                                    <th scope="col">dayLeave</th>
                                    <th scope="col">guaranteeFee</th>
                                    <th scope="col">methodPayment</th>
                                    <th colspan="3">Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items='${requestScope["bookingList"]}' var="guest">
                                    <tr>
                                        <td>${guest.getBookingCode()}</td>
                                        <td>${guest.getGuestName()}</td>
                                        <td>${guest.getPhoneNumber()}</td>
                                        <td>${guest.getDayArrival()}</td>
                                        <td>${guest.getDayLeave()}</td>
                                        <td>${guest.getGuaranteeFee()}</td>
                                        <td>${guest.getMethodPayment()}</td>
                                        <td>
                                            <button type="button" class="btn btn-outline-success" onclick='window.location.href="/system?action=checkIn&bookingCode=${guest.getBookingCode()}"'>
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-outline-success" onclick='window.location.href="/system?action=updateBookingList&bookingCode=${guest.getBookingCode()}"'>
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-outline-success" onclick='window.location.href="/system?action=removeBookingList&bookingCode=${guest.getBookingCode()}"'>
                                            </button>
                                        </td>
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
