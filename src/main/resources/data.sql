
-- USER
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('cabclientone@cab.com', 'Alex', 'Alexander', '123456', '0123456789', 'cabclientone');
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('cabclientwo@cab.nl', 'John', 'Doe', '222222', '1234567890', 'cabclienttwo');
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('cabclientthree@cab.org', 'Jan', 'de Wit', '111111', '2345678901', 'cabclientthree');
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('cabclientfour@cab.nl', 'Arjan', 'Roze', '333333', '3456789012', 'cabclientfour');
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('cabclientfive@cab.com', 'Cees', 'van Dame', '444444', '4567890123', 'cabclientfive');
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('cabclientsix@cab.nl', 'Henk', 'van de Pol', '555555', '5678901234', 'cabclientsix');

-- DRIVER
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('drivercabone@cab.nl', 'Ali', 'Roze', '555556', '6789012345', 'drivercabone');
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('drivercabtwo@cab.com', 'Cees', 'van Schaak', '666666', '7890123456', 'drivercabtwo');
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('drivercabthree@cab.nl', 'Suzan', 'van Straten', '377777', '8901234567', 'drivercabthree');
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('drivercabthree@cab.nl', 'Hans', 'de Driver', '477777', '9012345678', 'drivercabfour');

-- PLANNER
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('plannercabone@cab.nl', 'Piet', 'Pieters', '888888', '0123456789', 'plannercabone');
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('plannercabtwo@cab.com', 'Jan', 'Jansen', '999999', '1234567890', 'plannercabtwo');
INSERT INTO users (email, firstname, lastname, "password", phone, username) VALUES('plannercabthree@cab.nl', 'Suzan', 'Suzy', '000000', '2345678901', 'plannercabthree');

-- ROLE USER
INSERT INTO authorities (authority, username) VALUES('ROLE_USER', 'cabclientone');
INSERT INTO authorities (authority, username) VALUES('ROLE_USER', 'cabclienttwo');
INSERT INTO authorities (authority, username) VALUES('ROLE_USER', 'cabclientthree');
INSERT INTO authorities (authority, username) VALUES('ROLE_USER', 'cabclientfour');
INSERT INTO authorities (authority, username) VALUES('ROLE_USER', 'cabclientfive');
INSERT INTO authorities (authority, username) VALUES('ROLE_USER', 'cabclientsix');

-- ROLE DRIVER
INSERT INTO authorities (authority, username) VALUES('ROLE_DRIVER', 'drivercabone');
INSERT INTO authorities (authority, username) VALUES('ROLE_DRIVER', 'drivercabtwo');
INSERT INTO authorities (authority, username) VALUES('ROLE_DRIVER', 'drivercabthree');
INSERT INTO authorities (authority, username) VALUES('ROLE_DRIVER', 'drivercabfour');

-- DRIVERS
INSERT INTO drivers (licence_number, username) VALUES('1111222233334444', 'drivercabone');
INSERT INTO drivers (licence_number, username) VALUES('2222333344441111', 'drivercabtwo');
INSERT INTO drivers (licence_number, username) VALUES('3333444411112222', 'drivercabthree');
INSERT INTO drivers (licence_number, username) VALUES('4444111122223333', 'drivercabfour');

-- ROLE PLANNER
INSERT INTO authorities (authority, username) VALUES('ROLE_PLANNER', 'plannercabone');
INSERT INTO authorities (authority, username) VALUES('ROLE_PLANNER', 'plannercabtwo');
INSERT INTO authorities (authority, username) VALUES('ROLE_PLANNER', 'plannercabthree');

-- PLANNER
INSERT INTO planners (employee_number, username) VALUES('1', 'plannercabone');
INSERT INTO planners (employee_number, username) VALUES('2', 'plannercabtwo');
INSERT INTO planners (employee_number, username) VALUES('3', 'plannercabthree');

-- CARS
INSERT INTO cars (available_seats, car_id, car_type, driver_username, license_plate, make, model) VALUES(4, 1001, 'SEDAN', 'drivercabone', 'SS-SS-11', 'Mercedes', 'E350D');
INSERT INTO cars (available_seats, car_id, car_type, driver_username, license_plate, make, model) VALUES(4, 1002, 'WAGON', 'drivercabtwo', 'WW-WW-22', 'Mercedes', 'E200D');
INSERT INTO cars (available_seats, car_id, car_type, driver_username, license_plate, make, model) VALUES(6, 1003, 'MINIBUS', 'drivercabthree', 'MB-MB-33', 'Mercedes', 'V-Klasse');
INSERT INTO cars (available_seats, car_id, car_type, driver_username, license_plate, make, model) VALUES(8, 1004, 'BIGBUS', 'drivercabfour', 'BB-BB-44', 'Volkswagen', 'Transpoter');

-- CUSTOMERS
INSERT INTO customers (feedback_id, address, username) VALUES(null, 'Klantenstraat 1, 1011 AA Amsterdam', 'cabclientone');
INSERT INTO customers (feedback_id, address, username) VALUES(null, 'Utrechtsestraat 2, Utrecht', 'cabclienttwo');
INSERT INTO customers (feedback_id, address, username) VALUES(null, 'Passagierstraat 3, Utrecht', 'cabclientthree');
INSERT INTO customers (feedback_id, address, username) VALUES(null, 'Lopenstraat 4, Utrecht', 'cabclientfour');
INSERT INTO customers (feedback_id, address, username) VALUES(null, 'Rennentraat 5, Rotterdam', 'cabclientfive');
INSERT INTO customers (feedback_id, address, username) VALUES(null, 'Stilstaanstraat 6, Maarssen', 'cabclientsix');

-- BOOKING REQUESTS
INSERT INTO booking_requests
(distance_in_km, km_price, number_of_people, trip_date, trip_price, trip_time, booking_id, trip_trip_id, booking_status, car_type, customer_username, from_city, from_hnumber, from_pcode, from_street, planner_username, to_city, to_hnumber, to_pcode, to_street)
VALUES(50.0, 0.0, 4, '2023-11-21', 0.0, '09:10:00', 1001, NULL, 'REQUEST', 'SEDAN', 'cabclientone', 'Amsterdam', '1', '1011 AA', 'Klantenstraat', 'plannercabone', 'Utrecht', '1', '3555 BB', 'Onbekendestraat');
INSERT INTO booking_requests
(distance_in_km, km_price, number_of_people, trip_date, trip_price, trip_time, booking_id, trip_trip_id, booking_status, car_type, customer_username, from_city, from_hnumber, from_pcode, from_street, planner_username, to_city, to_hnumber, to_pcode, to_street)
VALUES(10.0, 0.0, 6, '2023-11-22', 0.0, '10:10:00', 1002, NULL, 'REQUEST', 'MINIBUS', 'cabclientone', 'Utrecht', '1001', '3551 AB', 'Startstraat', 'plannercabtwo', 'Utrecht', '10', '3525 BA', 'Bestemmingstraat');
INSERT INTO booking_requests
(distance_in_km, km_price, number_of_people, trip_date, trip_price, trip_time, booking_id, trip_trip_id, booking_status, car_type, customer_username, from_city, from_hnumber, from_pcode, from_street, planner_username, to_city, to_hnumber, to_pcode, to_street)
VALUES(20.0, 0.0, 4, '2023-11-23', 0.0, '11:10:00', 1003, NULL, 'REQUEST', 'SEDAN', 'cabclienttwo', 'Utrecht', '5', '3525 AA', 'Rennerstraat', 'plannercabone', 'Utrecht', '25', '3505 BC', 'Stopstraat');
INSERT INTO booking_requests
(distance_in_km, km_price, number_of_people, trip_date, trip_price, trip_time, booking_id, trip_trip_id, booking_status, car_type, customer_username, from_city, from_hnumber, from_pcode, from_street, planner_username, to_city, to_hnumber, to_pcode, to_street)
VALUES(55.0, 0.0, 4, '2023-11-24', 0.0, '12:10:00', 1004, NULL, 'REQUEST', 'SEDAN', 'cabclientthree', 'Amsterdam', '1', '1011 AA', 'Klantenstraat', 'plannercabone', 'Utrecht', '1', '3555 BB', 'Onbekendestraat');
INSERT INTO booking_requests
(distance_in_km, km_price, number_of_people, trip_date, trip_price, trip_time, booking_id, trip_trip_id, booking_status, car_type, customer_username, from_city, from_hnumber, from_pcode, from_street, planner_username, to_city, to_hnumber, to_pcode, to_street)
VALUES(30.0, 0.0, 8, '2023-11-26', 0.0, '10:00:00', 1006, NULL, 'REQUEST', 'BIGBUS', 'cabclientfive', 'Utrecht', '22', '3514 TT', 'Beginstraat', 'plannercabone', 'Schiphol', '1', '1111 AA', 'Vertrekpassage');
INSERT INTO booking_requests
(distance_in_km, km_price, number_of_people, trip_date, trip_price, trip_time, booking_id, trip_trip_id, booking_status, car_type, customer_username, from_city, from_hnumber, from_pcode, from_street, planner_username, to_city, to_hnumber, to_pcode, to_street)
VALUES(15.0, 0.0, 4, '2023-11-25', 0.0, '08:10:00', 1005, NULL, 'REQUEST', 'WAGON', 'cabclientfour', 'Utrecht', '200', '3518 ZA', 'Runnerstraat', 'plannercabone', 'Breukelen', '100', '3655 KK', 'Straatweg');
