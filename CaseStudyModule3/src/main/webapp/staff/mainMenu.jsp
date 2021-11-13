<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css");
<!DOCTYPE html>
<html lang="en">
<head>
    <title>MainMenu</title>
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
                            </ol>
                        </div>
                        <h4 class="page-title">MainMenu</h4>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div>
                                <button id="add-new-btn" class="btn btn-lg btn-primary" onclick='window.location.href="/system?action=showRoomList"'>
                                    <i class="fas fa-bed"></i>
                                    <span>Room Status</span>
                                </button>
                                <button id="transfer-info-btn" class="btn btn-lg btn-primary" onclick='window.location.href="/system?action=bookingList"'>
                                    <i class="fas fa-address-book"></i>
                                    <span>Booking-List</span>
                                </button>
                            </div>
                        </div>
                        <div class="card-body">
                            <div>
                                <div class="alert alert-primary" role="alert">
                                    Total Guest Arrival today:
                                    ${requestScope["countArrivalGuest"]}
                                </div>
                                <div class="alert alert-warning" role="alert">
                                    Total Guest Departure today:
                                    ${requestScope["countDepartureGuest"]}
                                </div>
                            </div>
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