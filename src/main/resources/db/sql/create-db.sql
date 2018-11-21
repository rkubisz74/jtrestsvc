create table dane (
	id int not null generated always as identity,
	date timestamp not null,
	value float not null,
	deviceid int not null,
	eventdate timestamp not null,
	primary key(id)
);

create table log (
	id int not null generated always as identity,
	date timestamp not null,
	description varchar(256),
	primary key(id)
);

create table users (
	id int not null generated always as identity,
	username varchar(50) not null,
	password varchar(60) not null,
	enabled boolean,
	primary key(id)
);
