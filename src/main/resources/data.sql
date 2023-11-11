-- CARS
INSERT INTO cars (available_seats, car_id, car_type, license_plate, make, model) VALUES(4, 1001, 'SEDAN',  'SS-SS-11', 'Mercedes', 'E350D');
INSERT INTO cars (available_seats, car_id, car_type, license_plate, make, model) VALUES(4, 1002, 'WAGON',  'WW-WW-22', 'Mercedes', 'E200D');
INSERT INTO cars (available_seats, car_id, car_type, license_plate, make, model) VALUES(6, 1003, 'MINIBUS',  'MB-MB-33', 'Mercedes', 'V-Klasse');
INSERT INTO cars (available_seats, car_id, car_type, license_plate, make, model) VALUES(8, 1004, 'BIGBUS', 'BB-BB-44', 'Volkswagen', 'Transpoter');

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
INSERT INTO drivers (car_id, licence_number, username) VALUES(1001, '1111222233334444', 'drivercabone');
INSERT INTO drivers (car_id, licence_number, username) VALUES(1002, '2222333344441111', 'drivercabtwo');
INSERT INTO drivers (car_id, licence_number, username) VALUES(1003, '3333444411112222', 'drivercabthree');
INSERT INTO drivers (car_id, licence_number, username) VALUES(1004, '4444111122223333', 'drivercabfour');

-- ROLE PLANNER
INSERT INTO authorities (authority, username) VALUES('ROLE_PLANNER', 'plannercabone');
INSERT INTO authorities (authority, username) VALUES('ROLE_PLANNER', 'plannercabtwo');
INSERT INTO authorities (authority, username) VALUES('ROLE_PLANNER', 'plannercabthree');

-- PLANNER
INSERT INTO planners (employee_number, username) VALUES('1', 'plannercabone');
INSERT INTO planners (employee_number, username) VALUES('2', 'plannercabtwo');
INSERT INTO planners (employee_number, username) VALUES('3', 'plannercabthree');


-- CUSTOMERS
INSERT INTO customers (address, username) VALUES('Klantenstraat 1, 1011 AA Amsterdam', 'cabclientone');
INSERT INTO customers (address, username) VALUES('Utrechtsestraat 2, Utrecht', 'cabclienttwo');
INSERT INTO customers (address, username) VALUES('Passagierstraat 3, Utrecht', 'cabclientthree');
INSERT INTO customers (address, username) VALUES('Lopenstraat 4, Utrecht', 'cabclientfour');
INSERT INTO customers (address, username) VALUES('Rennentraat 5, Rotterdam', 'cabclientfive');
INSERT INTO customers (address, username) VALUES('Stilstaanstraat 6, Maarssen', 'cabclientsix');

--TRIPS
INSERT INTO trips (trip_id, driver_username) VALUES(3001, 'drivercabone');
INSERT INTO trips (trip_id, driver_username) VALUES(3002, 'drivercabone');
INSERT INTO trips (trip_id, driver_username) VALUES(3003, 'drivercabone');
INSERT INTO trips (trip_id, driver_username) VALUES(3004, 'drivercabtwo');
INSERT INTO trips (trip_id, driver_username) VALUES(3005, 'drivercabtwo');
INSERT INTO trips (trip_id, driver_username) VALUES(3006, 'drivercabtwo');

-- --FEEDBACKS
INSERT INTO public.feedbacks (rating, submit_date, feedback_id, customer_username, feedback) VALUES(5, '2023-10-15', 5001, null, 'It''s a nice driver');
INSERT INTO public.feedbacks (rating, submit_date, feedback_id, customer_username, feedback) VALUES(5, '2023-10-15', 5002, null, 'It''s a nice driver');
INSERT INTO public.feedbacks (rating, submit_date, feedback_id, customer_username, feedback) VALUES(5, '2023-10-15', 5003, null, 'It''s a nice driver');
INSERT INTO public.feedbacks (rating, submit_date, feedback_id, customer_username, feedback) VALUES(5, '2023-10-15', 5004, null, 'It''s a nice driver');
INSERT INTO public.feedbacks (rating, submit_date, feedback_id, customer_username, feedback) VALUES(5, '2023-10-15', 5005, null, 'It''s a nice driver');
INSERT INTO public.feedbacks (rating, submit_date, feedback_id, customer_username, feedback) VALUES(5, '2023-10-15', 5006, null, 'It''s a nice driver');

-- BOOKING REQUESTS
INSERT INTO public.bookingrequests
(distance_in_km, km_price, number_of_people, trip_date, trip_price, trip_time, booking_id, trip_trip_id, booking_status, car_type, customer_username, from_city, from_hnumber, from_pcode, from_street, planner_username, to_city, to_hnumber, to_pcode, to_street)
VALUES(50.0, 0.0, 4, '2023-11-21', 0.0, '09:10:00', 1001, 3001, 'CONFIRMED', 'SEDAN', 'cabclientone', 'Amsterdam', '1', '1011 AA', 'Klantenstraat', 'plannercabone', 'Utrecht', '1', '3555 BB', 'Onbekendestraat');
INSERT INTO bookingrequests
(distance_in_km, km_price, number_of_people, trip_date, trip_price, trip_time, booking_id, trip_trip_id, booking_status, car_type, customer_username, from_city, from_hnumber, from_pcode, from_street, planner_username, to_city, to_hnumber, to_pcode, to_street)
VALUES(10.0, 0.0, 6, '2023-11-22', 0.0, '10:10:00', 1002, 3002, 'CONFIRMED', 'MINIBUS', 'cabclientone', 'Utrecht', '1001', '3551 AB', 'Startstraat', 'plannercabone', 'Utrecht', '10', '3525 BA', 'Bestemmingstraat');
INSERT INTO bookingrequests
(distance_in_km, km_price, number_of_people, trip_date, trip_price, trip_time, booking_id, trip_trip_id, booking_status, car_type, customer_username, from_city, from_hnumber, from_pcode, from_street, planner_username, to_city, to_hnumber, to_pcode, to_street)
VALUES(20.0, 0.0, 4, '2023-11-23', 0.0, '11:10:00', 1003, 3003, 'REQUEST', 'SEDAN', 'cabclienttwo', 'Utrecht', '5', '3525 AA', 'Rennerstraat', 'plannercabone', 'Utrecht', '25', '3505 BC', 'Stopstraat');
INSERT INTO bookingrequests
(distance_in_km, km_price, number_of_people, trip_date, trip_price, trip_time, booking_id, trip_trip_id, booking_status, car_type, customer_username, from_city, from_hnumber, from_pcode, from_street, planner_username, to_city, to_hnumber, to_pcode, to_street)
VALUES(55.0, 0.0, 4, '2023-11-24', 0.0, '12:10:00', 1004, 3004, 'REQUEST', 'SEDAN', 'cabclientthree', 'Amsterdam', '1', '1011 AA', 'Klantenstraat', 'plannercabone', 'Utrecht', '1', '3555 BB', 'Onbekendestraat');
INSERT INTO bookingrequests
(distance_in_km, km_price, number_of_people, trip_date, trip_price, trip_time, booking_id, trip_trip_id, booking_status, car_type, customer_username, from_city, from_hnumber, from_pcode, from_street, planner_username, to_city, to_hnumber, to_pcode, to_street)
VALUES(30.0, 0.0, 8, '2023-11-26', 0.0, '10:00:00', 1006, 3005, 'REQUEST', 'BIGBUS', 'cabclientfive', 'Utrecht', '22', '3514 TT', 'Beginstraat', 'plannercabone', 'Schiphol', '1', '1111 AA', 'Vertrekpassage');
INSERT INTO bookingrequests
(distance_in_km, km_price, number_of_people, trip_date, trip_price, trip_time, booking_id, trip_trip_id, booking_status, car_type, customer_username, from_city, from_hnumber, from_pcode, from_street, planner_username, to_city, to_hnumber, to_pcode, to_street)
VALUES(15.0, 0.0, 4, '2023-11-25', 0.0, '08:10:00', 1005, 3006, 'REQUEST', 'WAGON', 'cabclientfour', 'Utrecht', '200', '3518 ZA', 'Runnerstraat', 'plannercabone', 'Breukelen', '100', '3655 KK', 'Straatweg');

