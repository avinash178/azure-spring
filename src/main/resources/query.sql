/*search query  get teacher id where department is = ..*/
select teacher_uid from teachers_data where subject_id = (select sid FROM subjects where departments_d_id=3 and sid=subject_id);