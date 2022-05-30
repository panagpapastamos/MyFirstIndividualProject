package individualprojectpartb.database;

import individualprojectpartb.entities.Assignment;
import individualprojectpartb.entities.Course;
import individualprojectpartb.entities.CourseAssignment;
import individualprojectpartb.entities.CourseTrainer;
import individualprojectpartb.entities.Student;
import individualprojectpartb.entities.StudentCourse;
import individualprojectpartb.entities.Trainer;
import individualprojectpartb.entities.StudentAssignment;
import java.util.List;
import java.util.Scanner;

public class UserDaoComplexLists extends UserDao {

    //printing the more complex lists
    public static void printingLists() {
        StudentDao studentDao = new StudentDao();
        TrainerDao trainerDao = new TrainerDao();
        CourseDao courseDao = new CourseDao();
        AssignmentDao assignmentDao = new AssignmentDao();
        Scanner input = new Scanner(System.in);
        String userChoices;
        Boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("1 : LIST OF STUDENTS IN MULTIPLE COURSES");
            System.out.println("2 : LIST OF STUDENTS PER COURSE");
            System.out.println("3 : LIST OF TRAINERS PER COURSE");
            System.out.println("4 : LIST OF ASSIGNMENTS PER COURSE");
            System.out.println("5 : LIST OF ASSIGNMENTS PER COURSE PER STUDENT");
            System.out.println("6 : GO BACK");
            System.out.println("\n");

            userChoices = input.nextLine();
            switch (userChoices) {
                case ("1"):
                    printStudents(studentDao.findStudentsInMultipleCourses());
                    System.out.println("\n");
                    break;
                case ("2"):
                    printStudentsPerCourse();
                    System.out.println("\n");
                    break;
                case ("3"):
                    printTrainersPerCourse();
                    System.out.println("\n");
                    break;
                case ("4"):
                    printAssignmentsPerCourse();
                    System.out.println("\n");
                    break;
                case ("5"):
                    printStudentsAssignmentsPerCourse();
                    break;
                case ("6"):
                    exitProgram = true;
                    break;
            }
        }
    }

    //creating data for the more complex lists
    public static void createData() {
        StudentDao studentDao = new StudentDao();
        TrainerDao trainerDao = new TrainerDao();
        CourseDao courseDao = new CourseDao();
        AssignmentDao assignmentDao = new AssignmentDao();
        Scanner input = new Scanner(System.in);
        String userChoices;
        Boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("1 : ASSIGN A STUDENT TO A COURSE");
            System.out.println("2 : ASSIGN A TRAINER TO A COURSE");
            System.out.println("3 : ASSIGN AN ASSIGNMENT TO A COURSE");
            System.out.println("4 : ASSIGN AN ASSIGNMENT TO A STUDENT");
            System.out.println("5 : GO BACK");
            System.out.println("\n");

            userChoices = input.nextLine();
            switch (userChoices) {
                case ("1"):
                    studentCourseCreation();
                    System.out.println("\n");
                    break;
                case ("2"):
                    trainerCourseCreation();
                    System.out.println("\n");
                    break;
                case ("3"):
                    assignmentCourseCreation();
                    System.out.println("\n");
                    break;
                case ("4"):
                    assignmentStudentCreation();
                    System.out.println("\n");
                    break;
                case ("5"):
                    exitProgram = true;
                    break;
            }
        }
    }

    //updating data for the more complex lists
    public static void updateData() {
        Scanner input = new Scanner(System.in);
        String userChoices;
        Boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("1 : UPDATE A STUDENT-COURSE REGISTRATION");
            System.out.println("2 : UPDATE A TRAINER-COURSE REGISTRATION");
            System.out.println("3 : UPDATE AN ASSIGNMENT-COURSE REGISTRATION");
            System.out.println("4 : UPDATE AN ASSIGNMENT-STUDENT REGISTRATION");
            System.out.println("5 : GO BACK");
            System.out.println("\n");

            userChoices = input.nextLine();
            switch (userChoices) {
                case ("1"):
                    studentCourseUpdate();
                    System.out.println("\n");
                    break;
                case ("2"):
                    trainerCourseUpdate();
                    System.out.println("\n");
                    break;
                case ("3"):
                    assignmentCourseUpdate();
                    System.out.println("\n");
                    break;
                case ("4"):
                    assignmentStudentUpdate();
                    System.out.println("\n");
                    break;
                case ("5"):
                    exitProgram = true;
                    break;
            }
        }
    }

    //deleting data for the more complex lists
    public static void deleteData() {
        Scanner input = new Scanner(System.in);
        String userChoices;
        Boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("1 : DELETE A STUDENT-COURSE REGISTRATION");
            System.out.println("2 : DELETE A TRAINER-COURSE REGISTARTION");
            System.out.println("3 : DELETE AN ASSIGNMENT-COURSE REGISTRATION");
            System.out.println("4 : DELETE AN ASSIGNMENT-STUDENT REGISTRATION");
            System.out.println("5 : GO BACK");
            System.out.println("\n");

            userChoices = input.nextLine();
            switch (userChoices) {
                case ("1"):
                    studentCourseDelete();
                    System.out.println("\n");
                    break;
                case ("2"):
                    trainerCourseDelete();
                    System.out.println("\n");
                    break;
                case ("3"):
                    courseAssignmentDelete();
                    System.out.println("\n");
                    break;
                case ("4"):
                    assignmentStudentDelete();
                    System.out.println("\n");
                    break;
                case ("5"):
                    exitProgram = true;
                    break;
            }
        }
    }

    //methods to print the more complex lists
     public static void printStudentsCourses(List<StudentCourse> list) {
        for (StudentCourse sc : list) {
            System.out.println(sc);
        }
    }

    public static void printTrainersCourses(List<CourseTrainer> list) {
        for (CourseTrainer ct : list) {
            System.out.println(ct);
        }
    }

    public static void printAssignmentsCourses(List<CourseAssignment> list) {
        for (CourseAssignment ca : list) {
            System.out.println(ca);
        }
    }

    public static void printAssignmentsStudents(List<StudentAssignment> list) {
        for (StudentAssignment sa : list) {
            System.out.println(sa);
        }
    }
    
    //creating a registration for the table that links students and courses
    public static void studentCourseCreation() {
        StudentDao studentDao = new StudentDao();
        CourseDao courseDao = new CourseDao();
        StudentCourseDao SCD = new StudentCourseDao();
        Scanner input = new Scanner(System.in);
        Boolean goBack = false;

        while (!goBack) {
            System.out.println("THESE ARE THE STUDENTS PICK ONE OF THEIR NUMBERS NEXT TO STID");
            printStudents(studentDao.findAll());
            Student student = studentDao.findById(tryInt());
            System.out.println("THESE ARE THE COURSES PICK ONE OF THEIR NUMBERS NEXT TO COID");
            printCourses(courseDao.findAll());
            Course course = courseDao.findById(tryInt());
            StudentCourse SC = new StudentCourse(student, course);
            SCD.create(SC);
            System.out.println("1 IF YOU ASSIGN ANOTHER STUDENT TO A COURSE:");
            System.out.println("2 TO GO BACK :");
            String userChoice = input.nextLine();
            switch (userChoice) {
                case ("1"):
                    continue;
                case ("2"):
                    goBack = true;
            }

        }
    }

    //creating a registration for the table that links trainers and courses
    public static void trainerCourseCreation() {
        TrainerDao trainerDao = new TrainerDao();
        CourseDao courseDao = new CourseDao();
        CourseTrainerDao TCD = new CourseTrainerDao();
        Scanner input = new Scanner(System.in);
        Boolean goBack = false;

        while (!goBack) {
            System.out.println("THESE ARE THE TRAINERS PICK ONE OF THEIR NUMBERS NEXT TO TRID");
            printTrainers(trainerDao.findAll());
            Trainer trainer = trainerDao.findById(tryInt());
            System.out.println("THESE ARE THE COURSES PICK ONE OF THEIR NUMBERS NEXT TO COID");
            printCourses(courseDao.findAll());
            Course course = courseDao.findById(tryInt());
            CourseTrainer SC = new CourseTrainer(course, trainer);
            TCD.create(SC);
            System.out.println("1 IF YOU ASSIGN ANOTHER TRAINER TO A COURSE:");
            System.out.println("2 TO GO BACK :");
            String userChoice = input.nextLine();
            switch (userChoice) {
                case ("1"):
                    continue;
                case ("2"):
                    goBack = true;
            }

        }
    }

    //creating a registration for the table that links assignments and courses
    public static void assignmentCourseCreation() {
        AssignmentDao assignmentDao = new AssignmentDao();
        CourseDao courseDao = new CourseDao();
        CourseAssignmentDao CAD = new CourseAssignmentDao();
        Scanner input = new Scanner(System.in);
        Boolean goBack = false;

        while (!goBack) {
            System.out.println("THESE ARE THE ASSIGNMENTS PICK ONE OF THEIR NUMBERS NEXT TO ASID");
            printAssignments(assignmentDao.findAll());
            Assignment assignment = assignmentDao.findById(tryInt());
            System.out.println("THESE ARE THE COURSES PICK ONE OF THEIR NUMBERS NEXT TO COID");
            printCourses(courseDao.findAll());
            Course course = courseDao.findById(tryInt());
            CourseAssignment CA = new CourseAssignment(course, assignment);
            CAD.create(CA);
            System.out.println("1 IF YOU ASSIGN ANOTHER ASSIGNMENT TO A COURSE:");
            System.out.println("2 TO GO BACK :");
            String userChoice = input.nextLine();
            switch (userChoice) {
                case ("1"):
                    continue;
                case ("2"):
                    goBack = true;
            }

        }
    }

    //creating a registration for the table that links students and assignments
    public static void assignmentStudentCreation() {
        AssignmentDao assignmentDao = new AssignmentDao();
        StudentDao studentDao = new StudentDao();
        StudentAssignmentDao SAD = new StudentAssignmentDao();
        Scanner input = new Scanner(System.in);
        Boolean goBack = false;

        while (!goBack) {
            System.out.println("THESE ARE THE ASSIGNMENTS PICK ONE OF THEIR NUMBERS NEXT TO ASID");
            printAssignments(assignmentDao.findAll());
            Assignment assignment = assignmentDao.findById(tryInt());
            System.out.println("THESE ARE THE STUDENTS PICK ONE OF THEIR NUMBERS NEXT TO STID");
            printStudents(studentDao.findAll());
            Student student = studentDao.findById(tryInt());
            StudentAssignment SA = new StudentAssignment(student, assignment);
            SAD.create(SA);
            System.out.println("1 IF YOU ASSIGN ANOTHER ASSIGNMENT TO A COURSE:");
            System.out.println("2 TO GO BACK :");
            String userChoice = input.nextLine();
            switch (userChoice) {
                case ("1"):
                    continue;
                case ("2"):
                    goBack = true;
            }

        }
    }

    //updating a registration for the table that links students and courses
    public static void studentCourseUpdate() {
        StudentCourseDao SCD = new StudentCourseDao();
        StudentDao sd = new StudentDao();
        CourseDao cd = new CourseDao();
        printStudentsCourses(SCD.findAll());
        System.out.println("PRESS THE NUMBER NEXT TO STCO_ID TO PICK THE REGISTRATION YOU WANT TO CHANGE");

        StudentCourse SC = SCD.findById(tryInt());
        Student student = SC.getStudent();
        System.out.println(student);
        System.out.println("PUT THE STID OF THE STUDENT YOU WANT TO PUT");
        student = sd.findById(tryInt());
        System.out.println("PUT THE COID OF THE COURSE YOU WANT TO PUT");
        Course course = SC.getCourse();
        course = cd.findById(tryInt());
        SC.setCourse(course);
        SC.setStudent(student);
        SCD.update(SC);

    }

    //updating a registration for the table that links trainers and courses
    public static void trainerCourseUpdate() {
        CourseTrainerDao CTD = new CourseTrainerDao();
        TrainerDao td = new TrainerDao();
        CourseDao cd = new CourseDao();
        printTrainersCourses(CTD.findAll());
        System.out.println("PRESS THE NUMBER NEXT TO COTR_ID TO PICK THE REGISTRATION YOU WANT TO CHANGE");

        CourseTrainer CT = CTD.findById(tryInt());
        Trainer trainer = CT.getTrainer();
        System.out.println(trainer);
        System.out.println("PUT THE TRID OF THE TRAINER YOU WANT TO PUT");
        trainer = td.findById(tryInt());
        System.out.println("PUT THE COID OF THE COURSE YOU WANT TO PUT");
        Course course = CT.getCourse();
        course = cd.findById(tryInt());

        CT.setCourse(course);
        CT.setTrainer(trainer);
        CTD.update(CT);

    }

    //updating a registration for the table that links assignments and courses
    public static void assignmentCourseUpdate() {
        CourseAssignmentDao CTD = new CourseAssignmentDao();
        AssignmentDao ad = new AssignmentDao();
        CourseDao cd = new CourseDao();
        printAssignmentsCourses(CTD.findAll());
        System.out.println("PRESS THE NUMBER NEXT TO COAS_ID TO PICK THE REGISTRATION YOU WANT TO CHANGE");

        CourseAssignment CT = CTD.findById(tryInt());
        Assignment assignment = CT.getAssignment();
        System.out.println(assignment);
        System.out.println("PUT THE ASID OF THE ASSIGNMENT YOU WANT TO PUT");
        assignment = ad.findById(tryInt());
        System.out.println("PUT THE COID OF THE COURSE YOU WANT TO PUT");
        Course course = CT.getCourse();
        course = cd.findById(tryInt());

        CT.setCourse(course);
        CT.setAssignment(assignment);
        CTD.update(CT);

    }

    //updating a registration for the table that links students and assignments
    public static void assignmentStudentUpdate() {
        StudentAssignmentDao CTD = new StudentAssignmentDao();
        StudentDao sd = new StudentDao();
        AssignmentDao ad = new AssignmentDao();
        printAssignmentsStudents(CTD.findAll());
        System.out.println("PRESS THE NUMBER NEXT TO STAS_ID TO PICK THE REGISTRATION YOU WANT TO CHANGE");

        StudentAssignment CT = CTD.findById(tryInt());
        Assignment assignment = CT.getAssignment();
        System.out.println(assignment);
        System.out.println("PUT THE ASID OF THE ASSIGNMENT YOU WANT TO PUT");
        assignment = ad.findById(tryInt());
        System.out.println("PUT THE STID OF THE STUDENT YOU WANT TO PUT");
        Student student = CT.getStudent();
        student = sd.findById(tryInt());

        CT.setStudent(student);
        CT.setAssignment(assignment);
        CTD.update(CT);

    }
    
    //deleting a registration for the table that links students and courses
     public static void studentCourseDelete() {
        StudentCourseDao SC = new StudentCourseDao();
        System.out.println("PRESS THE NUMBER NEXT TO STCO_ID TO PICK THE REGISTRATION YOU WANT TO DELETE");
        printStudentsCourses(SC.findAll());
        SC.delete(tryInt());
    }

     //deleting a registration for the table that links trainers and courses
    public static void trainerCourseDelete() {
        CourseTrainerDao TC = new CourseTrainerDao();
        System.out.println("PRESS THE NUMBER NEXT TO TRID TO PICK THE REGISTRATION YOU WANT TO DELETE");
        printTrainersCourses(TC.findAll());
        TC.delete(tryInt());
    }

    //deleting a registration for the table that links assignments and courses
    public static void courseAssignmentDelete() {
        CourseAssignmentDao CA = new CourseAssignmentDao();
        System.out.println("PRESS THE NUMBER NEXT TO COTR_ID TO PICK THE REGISTRATION YOU WANT TO DELETE");
        printAssignmentsCourses(CA.findAll());
        CA.delete(tryInt());
    }

    //deleting a registration for the table that links students and assignments
    public static void assignmentStudentDelete() {
        StudentAssignmentDao SA = new StudentAssignmentDao();
        System.out.println("PRESS THE NUMBER NEXT TO STAS_ID TO PICK THE REGISTRATION YOU WANT TO DELETE");
        printAssignmentsStudents(SA.findAll());
        SA.delete(tryInt());
    }

   

}
