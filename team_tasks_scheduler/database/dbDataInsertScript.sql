USE `java2_task_scheduler2` ;

INSERT INTO users (username, user_password, email, send_reminder)
VALUES ('1111', 'b59c67bf196a4758191e42f76670ceba', 'a@b.c', false);

INSERT INTO users (username, user_password, email, send_reminder)
VALUES ('2222', '934b535800b1cba8f96a5d72f72f1611', 't@t.t', false);

INSERT INTO users (username, user_password, email, send_reminder)
VALUES ('John Doe', '934b535800b1cba8f96a5d72f72f1611', 'c@b.a', false);

INSERT INTO users (username, user_password, email, send_reminder)
VALUES ('Jane Doe', '2be9bd7a3434f7038ca27d1918de58bd', 'qwery@abc.hh', false);

INSERT INTO users (username, user_password, email, send_reminder)
VALUES ('Thor Odinson', 'dbc4d84bfcfe2284ba11beffb853a8c4', 'abc123@in.xz', false);

INSERT INTO users (username, user_password, email, send_reminder)
VALUES ('123soldier', '6074c6aa3488f3c2dddff2a7ca821aab', 'must@year.ac', false);

INSERT INTO users (username, user_password, email, send_reminder)
VALUES ('007Bond', 'e9510081ac30ffa83f10b68cde1cac07', 'james@adam.al', false);

INSERT INTO users (username, user_password, email, send_reminder)
VALUES ('Nagibator666', 'd79c8788088c2193f0244d8f1f36d2db', 'bender6@box.at', false);

INSERT INTO users (username, user_password, email, send_reminder)
VALUES ('Star Destroyer', 'cf79ae6addba60ad018347359bd144d2', 'creater@vava.one', false);

INSERT INTO users (username, user_password, email, send_reminder)
VALUES ('Darth Vader', 'fa246d0262c3925617b0c72bb20eeb1d', 'anakin@walker.sky', false);





INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("first task task", 0, '2022-11-12 09:05:00', '2022-11-12 09:05:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("2nd task task", 2, '2022-11-30 08:10:00', '2023-12-01 20:40:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("3rd task task task", 0, '2023-12-30 08:10:00', '2023-12-30 08:10:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("appointment to dentist", 2, '2022-12-30 08:10:00', '2023-12-01 20:40:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("get car from a workshop", 0, '2023-01-10 09:05:00', '2023-01-10 09:05:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("check joga classes", 2, '2022-12-30 08:10:00', '2027-12-01 20:40:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("birth day of brother's son", 0, '2023-11-12 09:05:00', '2023-11-12 09:05:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("buy flower for colleague", 2, '2024-12-30 08:10:00', '2026-12-01 20:40:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("first day of the month", 0, '2023-12-12 09:05:00', '2023-12-12 09:05:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("valentine's day", 2, '2023-05-30 08:10:00', '2024-04-01 20:40:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("take my medication; every day", 0, '2023-03-12 09:05:00', '2023-03-12 09:05:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("download some movies", 0, '2023-03-12 09:05:00', '2023-03-12 09:05:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("buy chocolate", 2, '2022-12-30 08:10:00', '2023-01-10 20:40:00', 1002);


INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("take a shower", 2, '2022-11-30 08:10:00', '2022-12-20 20:40:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("tomorrow has to go to work:(", 2, '2022-12-10 08:10:00', '2022-12-22 20:40:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("after a month is vacation", 2, '2022-12-30 08:10:00', '2023-12-29 20:40:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("buy groceries", 2, '2022-12-30 08:10:00', '2023-12-10 20:40:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("pay bills and mortgage", 2, '2022-12-30 08:10:00', '2023-11-25 20:40:00', 1002);

INSERT INTO tasks (task_description, regularity, Due_date, End_date, user_id)
VALUES ("cinema in the evening", 2, '2023-12-30 08:10:00', '2024-12-01 20:40:00', 1002);




INSERT INTO settings (admin_password, email_from, email_password, email_host, email_port, email_protocol)
VALUES ("21232f297a57a5a743894a0e4a801fc3", "olegsktest@gmail.com", "glzblubwocovtifc", "smtp.gmail.com", "465", "ssl");
