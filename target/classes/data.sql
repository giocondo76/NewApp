INSERT INTO dev_type(
	dt_id, name)
	VALUES (1, 'tempreture'),
		(2, 'humidity'),
		(3, 'co2');


INSERT INTO loc_type(
	lt_id, name)
	VALUES (1, 'people lying or sitting are at rest'),
		(2, 'people are engaged in mental work, study'),
		(3, 'rooms with temporary stay of people - lobbies, walk-in closets, corridors, stairwells, bathrooms, smoking rooms, etc.');


INSERT INTO standart(
	st_id, name, temp_min, temp_max, hum_max, carb_diox_min, carb_diox_max)
	VALUES (1, 'warm', 18, 28, 65, 600, 1000),
	(2, 'cold', 18, 26, 60, 600, 1000);

INSERT INTO role (id, name , authority) VALUES
  (1, 'Users', 'ROLE_USER'),
  (2, 'Admin', 'ROLE_ADMIN');
INSERT INTO usr (id, name,email, password) VALUES
  (1, 'admin','a@a.ru','$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2');

INSERT INTO role_usr (usr_id, role_id) VALUES
  (1, 1),
  (1, 2);

INSERT INTO location(
	loc_id, name, type, standart, room_manager)
	VALUES (1, 'secret room', 3, 2, 1),
		(2, 'dinner room', 1, 2 , 1),
		(3, 'work space', 2, 2 , 1);
INSERT INTO device(
	dev_id, name, type, location)
	VALUES (1, 'device 1', 1, 1),
 		(2, 'device 2', 2, 1),
		(3, 'device 3', 3, 1),
		(4, 'device 4', 1, 2),
 		(5, 'device 5', 2, 2),
		(6, 'device 6', 3, 2),
		(7, 'device 7', 1, 3),
 		(8, 'device 8', 2, 3),
		(9, 'device 9', 3, 3);

INSERT INTO condition(
	cond_id, datetime, location, temp, hum, carb_diox)
	VALUES  (1,'2014-04-04 20:00:00', 1, 25, 40, 800),
	        (2,'2014-04-04 20:05:00', 1, 24, 41, 809),
		(3,'2014-04-04 20:10:00', 1, 25, 43, 811),
		(4,'2014-04-04 20:15:00', 1, 23, 47, 790),
		(5,'2014-04-04 20:20:00', 1, 26, 51, 803),
		(6,'2014-04-04 20:00:00', 2, 25, 50, 740),
		(7,'2014-04-04 20:05:00', 2, 23, 55, 740),
		(8,'2014-04-04 20:10:00', 2, 27, 55, 740),
		(9,'2014-04-04 20:15:00', 2, 24, 49, 790),
		(10,'2014-04-04 20:20:00', 2, 24, 47, 809),
		(11,'2014-04-04 20:00:00', 3, 21, 44, 750),
		(12,'2014-04-04 20:05:00', 3, 22, 44, 760),
		(13,'2014-04-04 20:10:00', 3, 23, 46, 761),
		(14,'2014-04-04 20:15:00', 3, 23, 50, 757),
		(15,'2014-04-04 20:20:00', 3, 25, 57, 743);

