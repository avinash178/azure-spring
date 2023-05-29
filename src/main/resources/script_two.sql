
ALTER TABLE teachers_lectures ALTER COLUMN lecture_done TYPE integer USING(lecture_done::integer);

UPDATE teachers_lectures SET lecture_done=0 where lecture_done::boolean='true';
UPDATE teachers_lectures SET lecture_done=1 where lecture_done::boolean='false';

ALTER TABLE teachers_lectures RENAME COLUMN lecture_done to lecture_status;



ALTER TABLE teachers_data  ADD COLUMN user_id bigint;
ALTER TABLE teachers_data 
    ADD CONSTRAINT teachers_data_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);
    
    
ALTER TABLE student_data  ADD COLUMN user_id bigint;
ALTER TABLE student_data 
    ADD CONSTRAINT student_data_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);
 
ALTER TABLE students_lectures  ADD COLUMN user_id bigint;
ALTER TABLE students_lectures 
    ADD CONSTRAINT students_lectures_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);
    
ALTER TABLE teachers_lectures  ADD COLUMN user_id bigint;
ALTER TABLE teachers_lectures 
    ADD CONSTRAINT teachers_lectures_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);

        
ALTER TABLE teachers_data ADD COLUMN department_id bigint;
    
ALTER TABLE teachers_data ADD CONSTRAINT department_id_teacher_data FOREIGN KEY (department_id) REFERENCES department_names (d_id);  
UPDATE teachers_data t SET department_id=(SELECT u.department_id FROM users u WHERE u.uidnumber=t.teacher_uid);
 
    



ALTER TABLE teachers_lectures RENAME COLUMN tid to lecture_id;
ALTER TABLE students_lectures RENAME COLUMN id to lecture_id;

ALTER TABLE students_lectures ADD COLUMN teacher_lec_ref bigint;
ALTER TABLE students_lectures ADD CONSTRAINT teacher_lec_ref_student_key FOREIGN KEY (teacher_lec_ref) REFERENCES teachers_lectures (lecture_id); 

CREATE TABLE assigned_lectures(
	id bigint,
	assign_to_whom_id bigint,
	teacher_lecture bigint,
	status integer,
	assign_teacher_name varchar(20),
	assigned_date  timestamp with time zone,
	
	primary key(id),
	foreign key(assign_To_Whom_Id) references users(user_id),
	foreign key(teacher_lecture) references teachers_lectures(lecture_id)
);

alter table assigned_lectures add column cancel_request boolean;

ALTER TABLE users DROP COLUMN uidnumber;
ALTER TABLE teachers_data DROP COLUMN teacher_uid;
ALTER TABLE student_data DROP COLUMN student_uid;
ALTER TABLE students_lectures DROP COLUMN student_uid;
ALTER TABLE teachers_lectures DROP COLUMN teacher_uid;

ALTER TABLE teachers_lectures DROP COLUMN lecture_uid;
    
