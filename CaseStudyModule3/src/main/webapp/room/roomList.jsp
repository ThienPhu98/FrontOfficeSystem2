<%@ page import="java.text.DecimalFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Booking List</title>
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
                                <li class="breadcrumb-item active">
                                    <i class="fas fa-list-alt"></i>
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
                                    <th colspan="3" style="text-align: center">Action</th>
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
                                            <button type="button" class="btn btn-success" onclick='window.location.href="/system?action=checkInWithReservation&bookingCode=${guest.getBookingCode()}"'>
                                                <i class="fas fa-user-check"></i>
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-warning" onclick='window.location.href="/system?action=updateReservation&id=${guest.getBookingCode()}"'>
                                                <i class="fas fa-user-edit"></i>
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-danger" onclick='window.location.href="/system?action=removeReservation&id=${guest.getBookingCode()}"'>
                                                <i class="fas fa-user-times"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <c:if test='${requestScope["message"] == "update"}'>
                            <div class="alert alert-success" role="alert" style="margin-left: 20px">
                                <span>Update Success!!!</span>
                            </div>
                        </c:if>
                        <c:if test='${requestScope["message"] == "remove"}'>
                            <div class="alert alert-success" role="alert" style="margin-left: 20px">
                                <span>Remove Success!!!</span>
                            </div>
                        </c:if>
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
