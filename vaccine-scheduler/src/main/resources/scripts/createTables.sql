create table users (
	user_id int not null primary key auto_increment,
    cnp varchar(13) not null unique,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    gender varchar(50),
    user_password varchar(255), 
    username varchar(50) not null unique,
    age int not null, 
    user_role varchar(20)
);

insert into users 
values(default, '1234567890123', 'fname', 'lname', 'male', "$2a$10$Ut3VMKAulZE1mzJCyi8Qjui46Z9F1Ww11GYY8M5lB6YV.qY48orvy", 
'username', 19, 'ROLE_USER');

create table vaccines (
	vaccine_id int not null primary key auto_increment,
    vaccine_name varchar(100) not null,
    vaccine_type varchar(50) not null,
    producer varchar(100),
    origin varchar(100) 
);

create table centers (
	center_id int not null primary key auto_increment,
    center_name varchar(100) not null,
    country varchar(50) not null,
    region varchar(50) not null,
    city varchar(50) not null,
    street varchar(50) not null,
    street_number int not null
);

create table user_schedules (
	user_schedule_id int not null primary key auto_increment,
    center_id int not null,
    user_id int not null,
    vaccine_id int not null,
    vaccine_date datetime not null,
    foreign key(center_id) references centers(center_id),
    foreign key (user_id) references users(user_id),
	foreign key (vaccine_id) references vaccines(vaccine_id)
);
