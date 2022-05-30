use individualprojb;
show tables;

INSERT INTO student (f_name,l_name,tuition_fees,date_of_birth)
			VALUES  ('PANAGIOTIS','PAPASTAMOS',2000,'2003-04-18'),
					('CHRISTOS','KONTOSIS',2000,'2003-05-13'),
                    ('ATHANASIOS','VAVATSIKOS',2000,'2003-06-12'),
                    ('DIMITRIS','SOULIOTIS',2000,'2003-01-02'),
                    ('VASILIKI','SPANOPOULOU',2000,'2003-04-22'),
                    ('MARIA','LYKOUDI',2000,'2004-06-18'),
                    ('CHARILAOS','PAPAS',2000,'2003-02-05'),
                    ('ANTONIS','MARKOULINOS',2000,'2003-06-02'),
                    ('GIANNIS','KOPANOS',2000,'2003-05-05'),
                    ('ATHANASIOS','PAVLIS',2000,'2003-05-18'),
                    ('LEYTERIS','MYSTAKIDIS',2000,'2003-09-13'),
                    ('KOSTAS','ROKKAS',2000,'2003-05-28'),
                    ('PANAGIOTIS','SOULIOTIS',2000,'2003-02-22'),
                    ('GIANNIS','GRIGORIOU',2000,'2004-01-09'),
                    ('CHRISTOS','KOTSOU',2000,'2003-07-03'),
                    ('GIORGOS','KARDAKAS',2000,'2003-09-13'),
                    ('FOTIS','SOLDATOS',2000,'2003-07-16'),
                    ('ANTONIS','MARKOULINOS',2000,'2003-09-02'),
                    ('MARIA','IAKOVIDOU',2000,'2004-02-10'),
                    ('VASILIS','BOSDELEKIDIS',2000,'2003-04-12');
                    
                    
INSERT INTO TRAINER (f_name,l_name,subject)
			VALUES ('GEORGE','IRAKLEIDIS','JAVA'),
				   ('SPYROS','MAYROS','JAVA'),
                   ('GEORGE','RIGOPOULOS','PYTHON'),
                   ('CHRISTOS','KAFES','PYTHON'),
                   ('VASILIS','KAFES','C#'),
                   ('CHRISTOS','PAPACHRISTOU','C#'),
                   ('NIKOLAOS','MIXAS','JAVASCRIPT'),
                   ('GERASIMOS','XATZIS','JAVASCRIPT');
                   
                 

INSERT INTO COURSE (title,stream,type,start_date,end_date)
			VALUES ('CB13','JAVA','PART-TIME','2021-02-15','2021-09-15'),
				   ('CB14','JAVA','FULL-TIME','2021-02-15','2021-05-15'),
                   ('C13','C#','PART-TIME','2021-03-15','2021-09-15'),
                   ('C14','C#','FULL-TIME','2021-03-15','2021-06-15'),
                   ('CP13','PYTHON','PART-TIME','2021-02-25','2021-08-25'),
                   ('CP14','PYTHON','FULL-TIME','2021-02-28','2021-05-28'),
                   ('CJ13','JAVASCRIPT','PART-TIME','2021-04-15','2021-10-15'),
                   ('CJ14','JAVASCRIPT','FULL-TIME','2021-04-15','2021-07-15');
                   
                  
INSERT INTO ASSIGNMENT (type,description,sub_date,oral_mark,total_mark)
		        VALUES ('JAVA_P_A','DESCRIPTION_1','2021-05-10',20,100),
                       ('JAVA_P_B','DESCRIPTION_2','2021-09-06',20,100),
                       ('JAVA_F_A','DESCRIPTION_3','2021-03-15',20,100),
                       ('JAVA_F_B','DESCRIPTION_4','2021-05-03',20,100),
                       ('C#_P_A','DESCRIPTION_5','2021-05-10',20,100),
                       ('C#_P_B','DESCRIPTION_6','2021-09-06',20,100),
                       ('C#_F_A','DESCRIPTION_7','2021-04-19',20,100),
                       ('C#_F_B','DESCRIPTION_8','2021-06-01',20,100),
                       ('PYTHON_P_A','DESCRIPTION_9','2021-05-17',20,100),
                       ('PYTHON_P_B','DESCRIPTION_10','2021-09-13',20,100),
                       ('PYTHON_F_A','DESCRIPTION_11','2021-03-22',20,100),
                       ('PYTHON_F_B','DESCRIPTION_12','2021-05-10',20,100),
                       ('JAVASC_P_A','DESCRIPTION_13','2021-06-07',20,100),
                       ('JAVASC_P_B','DESCRIPTION_14','2021-10-04',20,100),
                       ('JAVASC_F_A','DESCRIPTION_15','2021-06-07',20,100),
                       ('JAVASC_F_B','DESCRIPTION_16','2021-07-05',20,100);
                       
INSERT INTO course_assign (course_ID,assignment_ID)
				   VALUES (1,1),(1,2),(2,3),(2,4),
						  (3,5),(3,6),(4,7),(4,8),
                          (5,9),(5,10),(6,11),(6,12),
                          (7,13),(7,14),(8,15),(8,16);
                          
INSERT INTO course_trainer (course_ID,trainer_ID)
				   VALUES (1,1),(1,2),(2,1),(2,2),
						  (3,5),(3,6),(4,5),(4,6),
                          (5,3),(5,4),(6,3),(6,4),
                          (7,7),(7,8),(8,7),(8,8);
                          
 INSERT INTO student_course (student_ID,course_ID)
				   VALUES (1,1),(2,1),(3,1),
                          (4,2),(5,2),(6,2),
                          (7,3),(8,3),(9,3),
                          (10,4),(11,4),(12,4),
                          (13,5),(14,5),(15,5),
                          (16,6),(17,6),(18,6),
                          (17,7),(18,7),(19,7),
                          (20,8),(2,8),(6,8);                         
                          
                          
INSERT INTO student_assign (student_ID,assignment_ID)
				   VALUES (1,1),(2,1),(3,1),
                          (1,2),(2,2),(3,2),
                          (4,3),(5,3),(6,3),
                          (4,4),(5,4),(6,4),
                          (7,5),(8,5),(9,5),
                          (7,6),(8,6),(9,6),
                          (10,7),(11,7),(12,7),
                          (10,8),(11,8),(12,8),
                          (13,9),(14,9),(15,9),
                          (13,10),(14,10),(15,10),
                          (16,11),(17,11),(18,11),
                          (16,12),(17,12),(18,12),
                          (17,13),(18,13),(19,13),
                          (17,14),(18,14),(19,14),
                          (20,15),(2,15),(6,15),
                          (20,16),(2,16),(6,16);  
 


