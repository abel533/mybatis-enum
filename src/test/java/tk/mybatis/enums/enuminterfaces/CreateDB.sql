drop table users if exists;

create table users (
  id int,
  name varchar(20),
  sex varchar(10),
  constellations varchar(10)
);

insert into users (id, name, sex, constellations) values
(1, 'User1', 1, 3),
(2, 'User2', 2, 11);
