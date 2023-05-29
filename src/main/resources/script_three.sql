ALTER TABLE teachers_data DROP CONSTRAINT teachers_data_user_id;
ALTER TABLE teachers_data 
   ADD CONSTRAINT teachers_data_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
   on delete cascade on update cascade;
   
   
ALTER TABLE teachers_lectures DROP CONSTRAINT teachers_lectures_user_id;
ALTER TABLE teachers_lectures 
   ADD CONSTRAINT teachers_lectures_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
   on delete cascade on update cascade;
  
   
ALTER TABLE student_data DROP CONSTRAINT student_data_user_id;
ALTER TABLE student_data 
   ADD CONSTRAINT student_data_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
   on delete cascade on update cascade;
