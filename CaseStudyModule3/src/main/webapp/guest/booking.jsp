<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Booking</title>
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
                                    <span>Booking</span>
                                </li>
                            </ol>
                        </div>
                        <h4 class="page-title">Booking</h4>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <form class="row g-3" method="post">
                                <div class="col-md-6">
                                    <c:if test='${requestScope["checkName"] == "success"}'>
                                        <label for="validationDefault01" class="form-label">Guest name</label>
                                        <input type="text" class="form-control is-valid" id="validationServer01" value=${requestScope["fullName"]} name="guestName" required>
                                        <div class="valid-feedback">
                                            Guest Name Valid!
                                        </div>
                                    </c:if>
                                    <c:if test='${requestScope["checkName"] == "false"}'>
                                        <label for="validationDefault01" class="form-label">Guest name</label>
                                        <input type="text" class="form-control is-invalid" id="validationServer01" value=${requestScope["fullName"]} name="guestName" required>
                                        <div id="validationServer01Feedback" class="invalid-feedback">
                                            Guest Name Invalid! Make sure name doesn't have number or special character!
                                        </div>
                                    </c:if>
                                    <c:if test='${requestScope["checkName"] == null}'  >
                                        <label for="validationDefault01" class="form-label">Guest name</label>
                                        <input type="text" class="form-control" id="validationDefault01" placeholder="Nguyen Van A" name="guestName" required>
                                    </c:if>
                                </div>

                                <div class="col-md-6">
                                    <c:if test='${requestScope["checkPhoneNumber"] == "success"}'>
                                        <label for="validationDefault02" class="form-label">Guest Phone Number</label>
                                        <input type="text" class="form-control is-valid" id="validationServer02" value=${requestScope["phoneNumber"]} name="phoneNumber" required>
                                        <div class="valid-feedback">
                                            Phone Number Valid!
                                        </div>
                                    </c:if>
                                    <c:if test='${requestScope["checkPhoneNumber"] == "false"}'>
                                        <label for="validationDefault02" class="form-label">Guest Phone Number</label>
                                        <input type="text" class="form-control is-invalid" id="validationServer02" value=${requestScope["phoneNumber"]} name="phoneNumber" required>
                                        <div id="validationServer02Feedback" class="invalid-feedback">
                                            Phone Number Invalid! Make sure length of number is 10 or 11 and start at '0'!
                                        </div>
                                    </c:if>
                                    <c:if test='${requestScope["checkPhoneNumber"] == null}'  >
                                        <label for="validationDefault02" class="form-label">Guest Phone Number</label>
                                        <input type="number" class="form-control" id="validationDefault02" placeholder="0123123123" name="phoneNumber" required>
                                    </c:if>
                                </div>

                                <div class="col-md-6">
                                    <c:if test='${requestScope["checkArrivalDate"] == "success"}'>
                                        <label for="validationDefault03" class="form-label">Arrive Date</label>
                                        <input type="date" class="form-control is-valid" id="validationServer03" value= ${requestScope["arrivalDate"]} name="arrivalDate" required>
                                        <div class="valid-feedback">
                                            Arrive Date Valid!
                                        </div>
                                    </c:if>
                                    <c:if test='${requestScope["checkArrivalDate"] == "false"}'>
                                        <label for="validationDefault03" class="form-label">Arrive Date</label>
                                        <input type="date" class="form-control is-invalid" id="validationServer03" value= ${requestScope["arrivalDate"]} name="arrivalDate" required>
                                        <div id="validationServer03Feedback" class="invalid-feedback">
                                            Arrive Date Invalid! Make sure arrival date input is not in the pass!
                                        </div>
                                    </c:if>
                                    <c:if test='${requestScope["checkArrivalDate"] == null}'  >
                                        <label for="validationDefault03" class="form-label">Arrive Date</label>
                                        <input type="date" class="form-control" id="validationDefault03" name="arrivalDate" required>
                                    </c:if>
                                </div>

                                <div class="col-md-6">
                                    <c:if test='${requestScope["checkArrivalDate"] == "success"}'>
                                        <label for="validationDefault04" class="form-label">Departure Date</label>
                                        <input type="date" class="form-control is-valid" id="validationServer04" value=${requestScope["departureDate"]} name="departureDate" required>
                                        <div class="valid-feedback">
                                            Departure Date Valid!
                                        </div>
                                    </c:if>
                                    <c:if test='${requestScope["checkArrivalDate"] == "false"}'>
                                        <label for="validationDefault04" class="form-label">Departure Date</label>
                                        <input type="date" class="form-control is-invalid" id="validationServer04" value=${requestScope["departureDate"]} name="departureDate" required>
                                        <div id="validationServer04Feedback" class="invalid-feedback">
                                            Departure Date Invalid! Make sure departure date input is not before arrival day!
                                        </div>
                                    </c:if>
                                    <c:if test='${requestScope["checkDepartureDate"] == null}'  >
                                        <label for="validationDefault04" class="form-label">Departure Date</label>
                                        <input type="date" class="form-control" id="validationDefault04" name="departureDate" required>
                                    </c:if>
                                </div>

                                <div class="col-md-9">
                                    <c:if test='${requestScope["checkGuaranteeFee"] == "success"}'>
                                        <label for="validationServer01Username" class="form-label">Guarantee Fee</label>
                                        <div class="input-group">
                                            <input type="number" class="form-control is-valid" id="validationServer01Username" value=${requestScope["outPutGuaranteeFee"]} name="guaranteeFee" style="text-align: right"  required>
                                            <span class="input-group-text" id="inputGroupPrepend1">VND</span>
                                        </div>
                                        <div class="valid-feedback">
                                            Guarantee Fee Valid!
                                        </div>
                                    </c:if>
                                    <c:if test='${requestScope["checkGuaranteeFee"] == "false"}'>
                                        <label for="validationServer02Username" class="form-label">Guarantee Fee</label>
                                        <div class="input-group">
                                            <input type="number" class="form-control is-invalid" id="validationServer02Username" value=${requestScope["outPutGuaranteeFee"]} name="guaranteeFee" style="text-align: right" required>
                                            <span class="input-group-text" id="inputGroupPrepend2">VND</span>
                                        </div>
                                        <div id="validationServer05Feedback" class="invalid-feedback">
                                            Guarantee Fee Invalid! Make sure Guarantee Fee equal or more than 0 and less than 10 billion!
                                        </div>
                                    </c:if>
                                    <c:if test='${requestScope["checkGuaranteeFee"] == null}'>
                                        <label for="validationDefaultUsername" class="form-label">Guarantee Fee</label>
                                        <div class="input-group">
                                            <input type="number" class="form-control" id="validationDefaultUsername" placeholder="1000000" name="guaranteeFee" style="text-align: right" required>
                                            <span class="input-group-text" id="inputGroupPrepend3">VND</span>
                                        </div>
                                    </c:if>
                                </div>

                                <div class="col-md-3">
                                    <label for="validationDefault04" class="form-label">State</label>
                                    <select class="form-select" id="validationDefault06" name="methodPayment" required>
                                        <option value="Cash">Cash</option>
                                        <option value="Credit Card">Credit Card</option>
                                    </select>
                                </div>
                                <div class="col-2">
                                    <button class="btn btn-primary" type="submit">Submit booking</button>
                                </div>

                                <div class="col-10">
                                    <c:if test='${requestScope["message"] == "false"}'>
                                        <div class="alert alert-danger" role="alert" style="margin-left: 20px">
                                            <span>Booking Fail! please follow Instructor!</span>
                                        </div>
                                    </c:if>
                                    <c:if test='${requestScope["message"] == "success"}'>
                                        <div class="alert alert-success" role="alert" style="margin-left: 20px">
                                            <span>Booking Success!!!</span>
                                        </div>
                                    </c:if>
                                </div>

                            </form>
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
