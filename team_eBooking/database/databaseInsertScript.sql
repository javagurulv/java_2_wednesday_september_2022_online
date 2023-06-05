insert into clients (email, phoneNumber)
values ("liza.tolka@gmail.com", "0037126395758")

insert into clients (email, phoneNumber)
values ("alina.tolka@gmail.com", "0037126146341")

insert into clients (id, email, phoneNumber)
values (1003, "alina1.tolka@gmail.com", "0037126146342")


insert into appointments (masterName, typeOfService)
values ("alina", "classic manicure")

insert into appointments (id, masterName, typeOfService)
values (1002, "alina", "pedicure")

insert into client_appointments(client_id, appointment_id)
values (1003, 1002)




