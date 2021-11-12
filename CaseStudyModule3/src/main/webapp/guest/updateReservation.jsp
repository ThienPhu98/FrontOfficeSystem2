<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <h1>UPDATE RESERVATION</h1>
    <form method="post">
        <div>
            <span>Booking Code</span>
            <input type="text" placeholder="${customer.getBookingCode()}" name="bookingCode">
        </div>
        <div>
            <span>Full Name</span>
            <input type="text" placeholder="${customer.getFullName()}" name="fullName">
        </div>
        <div>
            <span>Phone Number</span>
            <input type="text" placeholder="${customer.getPhoneNumber()}" name="phoneNumber">
        </div>
        <div>
            <span>Arrival Date</span>
            <input type="text" placeholder="${customer.getArrivalDate()}" name="arrivalDate">
        </div>
        <div>
            <span>Departure Date</span>
            <input type="text" placeholder="${customer.getDepartureDate()}" name="departureDate">
        </div>
        <div>
            <span>guarantee Fee</span>
            <input type="number" placeholder="${customer.getGuaranteeFee()}" name="guaranteeFee">
        </div>
        <div>
            <span>methodPayment</span>
            <input type="text" placeholder="${customer.getMethodPayment()}" name="methodPayment">
        </div>
        <div>
            <input type="submit" value="Update">
        </div>
    </form>
</div>
</body>
</html>
