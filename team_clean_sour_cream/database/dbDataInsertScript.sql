-- VEHICLES
-- ID 1 (passenger car 1)
INSERT INTO vehicles (`vehicle_type`, `brand`, `model`, `year`, `colour`, `price`, `engine_type`, `plate_number`, `transmission_type`)
VALUES ('PASSENGER_CAR', 'Toyota', 'Camry', 1990, 'RED', 35.00, 'DIESEL', 'TC-1990', 'MANUAL');

INSERT INTO passenger_cars (`vehicle_id`, `passenger_amount`, `baggageAmount`, `doorsAmount`, `air_conditioning`)
VALUES (1, 2, 2, 4, 1);

-- ID 2 (passenger car 2)
INSERT INTO vehicles (`vehicle_type`, `brand`, `model`, `year`, `colour`, `price`, `engine_type`, `plate_number`, `transmission_type`)
VALUES ('PASSENGER_CAR', 'BMW', 'X5', 1999, 'BLACK', 40.00, 'PETROL', 'BX-1999', 'AUTOMATIC');

INSERT INTO passenger_cars (`vehicle_id`, `passenger_amount`, `baggageAmount`, `doorsAmount`, `air_conditioning`)
VALUES (2, 3, 3, 5, 1);

-- ID 3 (mini buss 1)
INSERT INTO vehicles (`vehicle_type`, `brand`, `model`, `year`, `colour`, `price`, `engine_type`, `plate_number`, `transmission_type`)
VALUES ('MINIBUS', 'Mercedes', 'Vito', 2000, 'WHITE', 50.00, 'DIESEL', 'MV-2000', 'MANUAL');

INSERT INTO mini_buses (`vehicle_id`, `passenger_amount`, `baggageAmount`, `doorsAmount`, `air_conditioning`)
VALUES (3, 10, 10, 3, 1);

-- ID 4 (mini buss 2)
INSERT INTO vehicles (`vehicle_type`, `brand`, `model`, `year`, `colour`, `price`, `engine_type`, `plate_number`, `transmission_type`)
VALUES ('MINIBUS', 'Ford', 'Transit', 1995, 'WHITE', 45.00, 'GAS', 'FT-1995', 'MANUAL');

INSERT INTO mini_buses (`vehicle_id`, `passenger_amount`, `baggageAmount`, `doorsAmount`, `air_conditioning`)
VALUES (4, 12, 15, 4, 0);

-- ID 5 (motorcycle 1)
INSERT INTO vehicles (`vehicle_type`, `brand`, `model`, `year`, `colour`, `price`, `engine_type`, `plate_number`,  `transmission_type`)
VALUES ('MOTORCYCLE', 'Kawasaki', 'Ninja', 1997, 'BLUE', 45.00, 'PETROL', 'KN-1997', 'MANUAL');

INSERT INTO motorcycles (`vehicle_id`, `passenger_amount`)
VALUES (5, 1);

-- ID 6 (motorcycle 2)
INSERT INTO vehicles (`vehicle_type`, `brand`, `model`, `year`, `colour`, `price`, `engine_type`, `plate_number`, `transmission_type`)
VALUES ('MOTORCYCLE', 'Suzuki', 'Hayabusa', 2002, 'BLUE', 50.00, 'PETROL', 'SH-2002', 'AUTOMATIC');

INSERT INTO motorcycles (`vehicle_id`, `passenger_amount`)
VALUES (6, 2);

-- ID 7 (car trailer 1)
INSERT INTO vehicles (`vehicle_type`, `brand`, `model`, `year`, `colour`, `price`, `engine_type`, `plate_number`, `transmission_type`)
VALUES ('CAR_TRAILER', 'Stealth', 'X1', 2010, 'GREEN', 15.00, 'NONE', 'SX-2010', 'NONE');

INSERT INTO car_trailers (`vehicle_id`, `deck_width_cm`, `deck_length_cm`, `deck_height_cm`, `empty_weight_kg`, `max_weight_kg`)
VALUES (7, 200, 250, 120, 300, 1000);

-- ID 8 (car trailer 1)
INSERT INTO vehicles (`vehicle_type`, `brand`, `model`, `year`, `colour`, `price`, `engine_type`, `plate_number`, `transmission_type`)
VALUES ('CAR_TRAILER', 'Homesteader', 'Z100', 2012, 'ORANGE', 20.00, 'NONE', 'HZ-2012', 'NONE');

INSERT INTO car_trailers (`vehicle_id`, `deck_width_cm`, `deck_length_cm`, `deck_height_cm`, `empty_weight_kg`, `max_weight_kg`)
VALUES (8, 250, 270, 150, 500, 3000);

-- CLIENTS
-- ID 1
INSERT INTO clients (`personal_id`, `name`, `surname`, `email`, `phone`)
VALUES ('100288-10800','Tom', 'Thompson','tom@gmail.com','+37129865324');

-- ID 2
INSERT INTO clients (`personal_id`, `name`, `surname`, `email`, `phone`)
VALUES ('201275-10702','Sara', 'Lee','lee@gmail.com','+37129500310');

-- DEALS
-- ID 1
INSERT INTO rent_deals (`client_id`, `vehicle_id`, `start_date`, `duration`, `end_date`, `cost`)
VALUES (1, 2, '2023-01-15', 6, '2023-01-20', 240.00);

-- ID 2
INSERT INTO rent_deals (`client_id`, `vehicle_id`, `start_date`, `duration`, `end_date`, `cost`)
VALUES (1, 2, '2023-01-21', 3, '2023-01-23', 120.00);

-- ID 3
INSERT INTO rent_deals (`client_id`, `vehicle_id`, `start_date`, `duration`, `end_date`, `cost`)
VALUES (2, 4, '2023-01-21', 3, '2023-01-23', 135.00);

-- ID 4
INSERT INTO rent_deals (`client_id`, `vehicle_id`, `start_date`, `duration`, `end_date`, `cost`)
VALUES (2, 4, '2023-01-15', 6, '2023-01-20', 270.00);

SELECT * FROM vehicles;
SELECT * FROM passenger_cars;
SELECT * FROM mini_buses;
SELECT * FROM motorcycles;
SELECT * FROM car_trailers;
SELECT * FROM clients;
SELECT * FROM rent_deals;