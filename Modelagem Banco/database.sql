CREATE DATABASE storage;

\c storage;

CREATE TABLE user_data(
	id SERIAL,
	login varchar(100),
	name varchar(150),
	password varchar(20),
	PRIMARY KEY(id)
);

INSERT INTO user_data (login, name, password) VALUES ('soldierjvx', 'Joao', '123');

CREATE TABLE item(
	id SERIAL,
	name varchar(150),
	description varchar(300),
	amount bigint,
	created_date date DEFAULT now(),
	last_update date DEFAULT now(),
	PRIMARY KEY(id)
);


