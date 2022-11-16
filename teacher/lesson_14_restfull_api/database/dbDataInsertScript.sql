insert into books(title, author)
values ("Title 1", "Author 1");

insert into books(title, author)
values ("Title 2", "Author 2");

insert into books(title, author)
values ("Title 3", "Author 3");

insert into books(id, title, author)
values (1004, "Title 4", "Author 4");

insert into books(id, title, author)
values (1005, "Title 5", "Author 5");



insert into readers(first_name, last_name)
values ('FirstName1', 'LastName1');

insert into readers(first_name, last_name)
values ('FirstName2', 'LastName2');

insert into readers(id, first_name, last_name)
values (7777, 'FirstName3', 'LastName3');



insert into reader_books(reader_id, book_id, book_out_date)
values (7777, 1005, '2021-01-01 23:59:59');
