CREATE DATABASE FrontOfficeSystem;
USE FrontOfficeSystem;

CREATE TABLE Guests(
bookingCode VARCHAR (5) UNIQUE PRIMARY KEY,
guestName VARCHAR(60),
phoneNumber VARCHAR(11),
dayArrival DATE,
dayLeave DATE,
guaranteeFee DECIMAL(10,0),
methodPayment VARCHAR(15)
);

CREATE TABLE Rooms (
roomNumber VARCHAR(3) UNIQUE,
roomType VARCHAR(3),
roomStatus VARCHAR(3),
roomPrice DECIMAL(10,0),
isAvailable VARCHAR(5),
guestId VARCHAR(5)
);

CREATE TABLE Staffs (
staffId VARCHAR (5) UNIQUE PRIMARY KEY,
staffPassword VARCHAR(40),
staffLevel VARCHAR(10),
staffName VARCHAR(60),
staffPhone VARCHAR(11),
isValid VARCHAR(5)
);

CREATE TABLE Staff_History(
staffId VARCHAR (5),
staffAction VARCHAR (100),
Execution_date DATETIME
);

CREATE TABLE Back_up_Guest_Info(
bookingCode VARCHAR (5) UNIQUE PRIMARY KEY,
guestName VARCHAR(60),
phoneNumber VARCHAR(11),
dayArrival DATE,
dayLeave DATE,
guaranteeFee DECIMAL(10,0),
methodPayment VARCHAR(15)
);

INSERT INTO Rooms
VALUE ("101", "STD", "VR", 700000, "true", "empty"),
("102", "STD", "VR", 700000, "true", "empty"),
("103", "STD", "VR", 800000, "true", "empty"),
("104", "STD", "VR", 700000, "true", "empty"),
("201", "SUP", "VR", 900000, "true", "empty"),
("202", "SUP", "VR", 1100000, "true", "empty"),
("203", "SUP", "VR", 1200000, "true", "empty"),
("301", "DLX", "VR", 1200000, "true", "empty"),
("302", "DLX", "VR", 1400000, "true", "empty"),
("303", "DLX", "VR", 1500000, "true", "empty"),
("401", "SUT", "VR", 2000000, "true", "empty"),
("402", "SUT", "VR", 2000000, "true", "empty");

INSERT INTO Staffs
VALUE ("00000", "123", "Admin", "Nguyen Phan Thien Phu", "0773416607", "true"),
("11111", "321", "Manager", "Nguyen Minh Tuan", "07223432409", "true"),
("22222", "231", "Staff", "Nguyen Thanh Dat", "0977123422", "true");