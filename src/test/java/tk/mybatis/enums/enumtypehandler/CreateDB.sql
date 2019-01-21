drop table users if exists;

create table users (
  id int,
  name varchar(20),
  sex varchar(10)
);

insert into users (id, name, sex) values
(1, 'User1', 'MALE'),
(2, 'User2', 'FEMALE');
