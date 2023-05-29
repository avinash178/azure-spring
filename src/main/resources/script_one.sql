drop table if exists users;
drop table if exists user_roles;
drop table if exists students_lectures;
drop table if exists teachers_lectures;
drop table if exists student_data;
drop table if exists teachers_data;
drop table if exists subjects;
drop table if exists department_names;

/*create user_roles table */

create table IF NOT EXISTS user_roles(
	role_id bigint not null,
	role_name varchar(30),
	primary key(role_id)
);

create table IF NOT EXISTS department_names(
	d_id bigint not null,
	department_name varchar(50),
	primary key(d_id)
);




/*create users table */

create table IF NOT EXISTS users(
	user_id bigint not null,
	uidnumber varchar(30),
	email varchar(30),
	first_name varchar(30),
	is_locked boolean,
	last_name varchar(30),
	location varchar(60),
	password varchar(200),
	phone_number bigint not null,
	user_name varchar(30),
	department_id bigint,
	role_id bigint,
	primary key(user_id),
	foreign key(role_id) references user_roles(role_id),
	foreign key(department_id) references department_names(d_id)
);



create table IF NOT EXISTS subjects(
	sid bigint not null,
	subject_name varchar(50),
	departments_d_id bigint,
	primary key(sid),
	foreign key(departments_d_id) references department_names(d_id)
);


create table IF NOT EXISTS teachers_data(
	id bigint not null,
	teacher_uid varchar(30),
	subject_id bigint,
	primary key(id),
	foreign key(subject_id) references subjects(sid)
);


create table IF NOT EXISTS student_data(
	id bigint not null,
	student_uid varchar(50),
	year integer,
	primary key(id)
);


create table IF NOT EXISTS students_lectures(
	id bigint not null,
	attendance boolean not null,
	class_year integer,
	lecture_end TIMESTAMPTZ,
	lecture_uid varchar(50),
	notes varchar(200),
	lecture_start TIMESTAMPTZ,
	student_uid varchar(40),
	teacher_name varchar(40),
	department_id bigint,
	subject_id bigint,
	primary key(id),
	foreign key(department_id) references department_names(d_id),
	foreign key(subject_id) references subjects(sid)
	
);


create table IF NOT EXISTS teachers_lectures(
	tid bigint not null,
	class_year integer,
	lecture_end TIMESTAMPTZ,
	request_for_other boolean,
	lecture_done boolean,
	lecture_uid varchar(50),
	notes varchar(200),
	lecture_start TIMESTAMPTZ,
	teacher_name varchar(40),
	teacher_uid varchar(40),
	department_data bigint,
	subject_id bigint,
	primary key(tid),
	foreign key(department_data) references department_names(d_id),
	foreign key(subject_id) references subjects(sid)
);





insert into user_roles values(0,'ADMIN'),(1,'TEACHER'),(2,'STUDENT'),(3,'HOD');

insert into department_names values(1,'Chemical'),(2,'Civil'),(3,'Electrical'),(4,'Mechanical'),(5,'Information Technology'),(6,'Computer Science');

/*chemical department subjects  1 to 10 */
insert into subjects values(1,'Inorganic Chemistry',1),(2,'Organic Chemistry',1),(3,'Analytical Chemistry',1),(4,'Physical Chemistry',1);

/*civil eng subjects  11 to 20 */
insert into subjects values(11,'Strength of Materials',2),(12,'Building Material ',2),(13,'Design of Reinforced Concrete Structure',2),(14,'Hydraulics ',2),(15,'Concrete Technology',2),(16,'Solid Mechanics',2);

/*Electrical subjects 21 to 30 */
insert into subjects values(21,'Digital Electronic Circuits',3),(22,'Elements of Electrical Engineering',3),(23,'Power System',3),(24,'Control System Engineering ',3),(25,'Analog Electronic Circuits',3),(26,'ICT Tools and Security',3);

/*mechanical department subjects 31 to 40 */
insert into subjects values(31,'Heat Transfer',4),(32,'Fluid Mechanics',4),(33,' Machine Drawing',4),(34,'Fluid Machinery',4),(35,'Kinematic Analysis and Synthesis',4);

/*IT subjects 41 to 50 */
insert into subjects values(41,'Introduction to programming',5),(42,'Computer Networking',5),(43,'Networking and Internet Environment',5),(44,'Oracle and RDBMS',5),(45,'Operating System Concepts',5),(46,'Computational Mathematics',5);

/* computer science subjects 51 to 60 */
insert into subjects values(61,'Python',6),(62,'Java',6),(63,'C++',6),(64,'Networking',6),(65,'database',6),(66,'Object-Oriented Programming',6),(67,'Web Programming',6);


insert into users values(1,'1234567850','admin@gmail.com','jack',false,'maa','honk kong','$2a$10$NNcJ4.o1e8bEZdEXcCfw2eZhBClVqLb71wH6h5EWL0SWXHPOIalti',8978657865,'admin',6,0);
insert into users values(2,'1234578980','raj@gmail.com','raj',false,'patil','navi mumbai','$2a$10$XAH6AJjp7VRWAq7/bCud5utyNChUTtEQ6VtbSSK8ogI5BthvIvGaW',9876543223,'raj123',5,1);
insert into users values(3,'2234578980','rahul@gmail.com','rahul',false,'bhilare','satara','$2a$10$XAH6AJjp7VRWAq7/bCud5utyNChUTtEQ6VtbSSK8ogI5BthvIvGaW',9876543223,'rahul123',6,2);


/*teachers_data table only for teaches and their subject */
insert into teachers_data values(1,'1234578980',42);
insert into student_data values(1,'2234578980',0);


/*lecture table */

insert into teachers_lectures values(1,0,'2023-02-08 15:30:00',false,false,'9876542354','notes','2023-02-08 14:30:00','raj patil','1234578980',6,66);
insert into teachers_lectures values(2,0,'2023-02-09 15:30:00',false,false,'9876542353','notes','2023-02-09 14:30:00','raj patil','1234578980',6,65);
insert into teachers_lectures values(3,0,'2023-02-10 15:30:00',false,false,'9876542352','notes','2023-02-10 14:30:00','raj patil','1234578980',6,63);
insert into teachers_lectures values(4,0,'2023-02-10 13:30:00',false,false,'9876542351','notes','2023-02-10 12:30:00','raj patil','1234578980',6,61);

