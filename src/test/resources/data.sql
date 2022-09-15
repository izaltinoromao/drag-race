DELETE FROM drag;

INSERT INTO drag(id, driver, drag_time, speed_trap, car_year, make, model, type)
VALUES('123', 'Netim', 15.65, 136.89, 2019, 'BMW', 'M3', 'Sedan');

INSERT INTO time_winner(race, driver, model, drag_time)
VALUES (75, 'Joao', 'Model 3', 24.58);

INSERT INTO speed_winner(race, driver, model, speed_trap)
VALUES (75, 'Joao', 'Model 3', 145.58);