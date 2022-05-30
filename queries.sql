use individualprojb;

#list of all the students

select * from student;

#list of all the trainers

select * from trainer;

#list of all the assignments

select * from assignment;

#list of all the courses

select * from course;

#All the students per course

select course.title, course.stream, course.type, student.f_name, student.l_name 
from student_course
inner join course on student_course.course_id=course.COID
inner join student on student_course.student_id=student.STID;

#all the students per one course 

select student.stid, student.f_name, student.l_name, student.tuition_fees,student.date_of_birth
from student_course
inner join course on student_course.course_id=course.COID AND course.COID=2
inner join student on student_course.student_id=student.STID;


# All the trainers per course

select course.title, course.stream, course.type,trainer.f_name,trainer.l_name,trainer.subject 
from course_trainer
inner join course on course_trainer.course_id=course.COID
inner join trainer on course_trainer.trainer_id=trainer.TRID;


#all the trainer per one course

select trainer.TRID,trainer.f_name,trainer.l_name,trainer.subject 
from course_trainer
inner join course on course_trainer.course_id=course.COID AND course.COID=2
inner join trainer on course_trainer.trainer_id=trainer.TRID;


# All the assignments per course

select course.title, course.stream, course.type,assignment.description,assignment.type,assignment.sub_date
from course_assign
inner join course on course_assign.course_id=course.COID
inner join assignment on course_assign.assignment_id=assignment.ASID;

#all the assignments per one course

select assignment.ASID,assignment.type,assignment.description,assignment.sub_date,assignment.oral_mark,assignment.total_mark
from course_assign
inner join course on course_assign.course_id=course.COID AND course.COID=2
inner join assignment on course_assign.assignment_id=assignment.ASID;


# All the assignments per course per student

select course.title, course.stream, course.type,student.f_name,student.l_name, assignment.description,assignment.type,assignment.sub_date
from course_assign
inner join course on course_assign.course_id=course.COID
inner join assignment on course_assign.assignment_id=assignment.ASID
inner join student_assign on student_assign.assignment_id=assignment.ASID
inner join student on student_assign.student_id=student.STID;

#all the assignments per course per one student

select course.COID,course.title, course.stream, course.type, course.start_date,course.end_date,
assignment.ASID,assignment.type,assignment.description,assignment.sub_date,assignment.oral_mark,assignment.total_mark
from course_assign
inner join course on course_assign.course_id=course.COID
inner join assignment on course_assign.assignment_id=assignment.ASID
inner join student_assign on student_assign.assignment_id=assignment.ASID
inner join student on student_assign.student_id=student.STID AND student.STID=4;


# A list of students that belong to more than one courses

SELECT student.stid, student.f_name, student.l_name, student.tuition_fees,student.date_of_birth 
from student
inner join (SELECT student_id FROM student_course group by student_id having count(student_id)>1) 
AS ST ON student.STID=ST.student_id;




