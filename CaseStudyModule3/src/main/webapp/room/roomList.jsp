<%@ page import="java.text.DecimalFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Room List</title>
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
                                    <i class="fas fa-bed"></i>
                                    <span>Room List</span>
                                </li>
                            </ol>
                        </div>
                        <h4 class="page-title">Room List</h4>
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
                                    <th scope="col">roomType</th>
                                    <th scope="col">roomStatus</th>
                                    <th scope="col">roomPrice</th>
                                    <th scope="col">guestId</th>
                                    <th colspan="2" style="text-align: center">Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items='${requestScope["roomList"]}' var="room">
                                    <tr>
                                        <td>${room.getRoomNumber()}</td>
                                        <td>${room.getRoomType()}</td>
                                        <td>${room.getRoomStatus()}</td>
                                        <td>${room.getRoomPrice()}</td>
                                        <td>${room.getGuestId()}</td>
                                        <td style="text-align: center">
                                            <button type="button" class="btn btn-warning" onclick='window.location.href="/system?action=roomUpdate&id=${room.getRoomNumber()}"'>
                                                <i class="fas fa-pen-square"></i>
                                            </button>
                                        </td>
                                        <td style="text-align: center">
                                            <button type="button" class="btn btn-danger" onclick='window.location.href="/system?action=checkOut&id=${room.getRoomNumber()}"'>
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
                        <c:if test='${requestScope["message"] == "check-Out"}'>
                            <div class="alert alert-success" role="alert" style="margin-left: 20px">
                                <span>Check-Out Success!!!</span>
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
