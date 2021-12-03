drop table if exists treatment CASCADE;
create table treatment
(
	id integer auto_increment,
	description varchar(255),
	duration integer not null,
	name varchar(255),
	price double not null,
	primary key (id)
);