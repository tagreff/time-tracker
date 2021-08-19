create schema if not exists time_tracker;

create table if not exists time_tracker.users
(
    id serial primary key,
    login varchar(30) unique not null,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    password varchar(128) not null,
    role_id int not null
);

create table if not exists time_tracker.roles
(
    id serial primary key,
    name varchar(30) unique not null
);

create table if not exists time_tracker.tasks
(
    id serial primary key,
    user_id int not null,
    date date not null,
    description varchar(255) not null,
    hours int,
    minutes int
);

alter table time_tracker.users
	add constraint users_roles_id_fk
		foreign key (role_id) references time_tracker.roles(id);

alter table time_tracker.tasks
	add constraint tasks_users_id_fk
		foreign key (user_id) references time_tracker.users(id);