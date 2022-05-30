package individualprojectpartb.database;

import individualprojectpartb.entities.Assignment;
import individualprojectpartb.entities.Course;
import individualprojectpartb.entities.Student;
import individualprojectpartb.entities.Trainer;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserDao {

    //printing the 4 basic lists of the database
    public static void printingLists() {
        StudentDao studentDao = new StudentDao();
        TrainerDao trainerDao = new TrainerDao();
        CourseDao courseDao = new CourseDao();
        AssignmentDao assignmentDao = new AssignmentDao();
        Scanner input = new Scanner(System.in);
        String userChoices;
        Boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("1 : LIST OF STUDENTS");
            System.out.println("2 : LIST OF TRAINERS");
            System.out.println("3 : LIST OF COURSES");
            System.out.println("4 : LIST OF ASSIGNMENTS");
            System.out.println("5 : MORE LISTS");
            System.out.println("6 : GO BACK");
            System.out.println("\n");

            userChoices = input.nextLine();
            switch (userChoices) {
                case ("1"):
                    printStudents(studentDao.findAll());
                    System.out.println("\n");
                    break;
                case ("2"):
                    printTrainers(trainerDao.findAll());
                    System.out.println("\n");
                    break;
                case ("3"):
                    printCourses(courseDao.findAll());
                    System.out.println("\n");
                    break;
                case ("4"):
                    printAssignments(assignmentDao.findAll());
                    System.out.println("\n");
                    break;
                case ("5"):
                    UserDaoComplexLists.printingLists();
                    System.out.println("\n");
                    break;
                case ("6"):
                    exitProgram = true;
                    break;
            }
        }
    }

    //changing data in the lists of the database
    public static void changingLists() {
        Scanner input = new Scanner(System.in);
        String userChoices;
        Boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("1 : CREATE DATA");
            System.out.println("2 : UPDATE EXISTING DATA");
            System.out.println("3 : DELETE EXISTING DATA");
            System.out.println("4 : GO BACK");
            System.out.println("\n");

            userChoices = input.nextLine();
            switch (userChoices) {
                case ("1"):
                    createData();
                    System.out.println("\n");
                    break;
                case ("2"):
                    updateData();
                    System.out.println("\n");
                    break;
                case ("3"):
                    deleteData();
                    System.out.println("\n");
                    break;
                case ("4"):
                    exitProgram = true;
                    break;
            }
        }
    }

    //creating one of the 4 basic entities
    public static void createData() {
        Scanner input = new Scanner(System.in);
        String userChoices;
        Boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("1 : CREATE A STUDENT");
            System.out.println("2 : CREATE A TRAINER");
            System.out.println("3 : CREATE A COURSE");
            System.out.println("4 : CREATE AN ASSIGNMENT");
            System.out.println("5 : MORE LISTS");
            System.out.println("6 : GO BACK");
            System.out.println("\n");

            userChoices = input.nextLine();
            switch (userChoices) {
                case ("1"):
                    studentCreation();
                    System.out.println("\n");
                    break;
                case ("2"):
                    trainerCreation();
                    System.out.println("\n");
                    break;
                case ("3"):
                    courseCreation();
                    System.out.println("\n");
                    break;
                case ("4"):
                    assignmentCreation();
                    System.out.println("\n");
                    break;
                case ("5"):
                    UserDaoComplexLists.createData();
                    System.out.println("\n");
                    break;
                case ("6"):
                    exitProgram = true;
                    break;
            }
        }
    }

    //updating one of the 4 basic entities
    public static void updateData() {
        Scanner input = new Scanner(System.in);
        String userChoices;
        Boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("1 : UPDATE A STUDENT");
            System.out.println("2 : UPDATE A TRAINER");
            System.out.println("3 : UPDATE A COURSE");
            System.out.println("4 : UPDATE AN ASSIGNMENT");
            System.out.println("5 : MORE LISTS");
            System.out.println("6 : GO BACK");
            System.out.println("\n");

            userChoices = input.nextLine();
            switch (userChoices) {
                case ("1"):
                    studentUpdate();
                    System.out.println("\n");
                    break;
                case ("2"):
                    trainerUpdate();
                    System.out.println("\n");
                    break;
                case ("3"):
                    courseUpdate();
                    System.out.println("\n");
                    break;
                case ("4"):
                    assignmentUpdate();
                    System.out.println("\n");
                    break;
                case ("5"):
                    UserDaoComplexLists.updateData();
                    System.out.println("\n");
                    break;
                case ("6"):
                    exitProgram = true;
                    break;
            }
        }
    }

    //deleting one of 4 basic entities
    public static void deleteData() {
        Scanner input = new Scanner(System.in);
        String userChoices;
        Boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("1 : DELETE A STUDENT");
            System.out.println("2 : DELETE A TRAINER");
            System.out.println("3 : DELETE A COURSE");
            System.out.println("4 : DELETE AN ASSIGNMENT");
            System.out.println("5 : MORE LISTS");
            System.out.println("6 : GO BACK");
            System.out.println("\n");

            userChoices = input.nextLine();
            switch (userChoices) {
                case ("1"):
                    studentDelete();
                    System.out.println("\n");
                    break;
                case ("2"):
                    trainerDelete();
                    System.out.println("\n");
                    break;
                case ("3"):
                    courseDelete();
                    System.out.println("\n");
                    break;
                case ("4"):
                    assignmentDelete();
                    System.out.println("\n");
                    break;
                    case ("5"):
                    UserDaoComplexLists.deleteData();
                    System.out.println("\n");
                    break;
                case ("6"):
                    exitProgram = true;
                    break;
            }
        }
    }

    public static void printStudents(List<Student> list) {
        for (Student student : list) {
            System.out.println(student);
        }
    }

    public static void printCourses(List<Course> list) {
        for (Course course : list) {
            System.out.println(course);
        }
    }

    public static void printAssignments(List<Assignment> list) {
        for (Assignment assignment : list) {
            System.out.println(assignment);
        }
    }

    public static void printTrainers(List<Trainer> list) {
        for (Trainer trainer : list) {
            System.out.println(trainer);
        }
    }

    
    //printing more complex lists
    public static void printStudentsPerCourse() {
        CourseDao courseDao = new CourseDao();
        for (Course course : courseDao.findAll()) {
            System.out.println(course);
            List<Student> list = courseDao.findStudentsPerCourse(course.getCoId());
            printStudents(list);
            System.out.println("\n");
        }

    }

    public static void printTrainersPerCourse() {
        CourseDao courseDao = new CourseDao();
        for (Course course : courseDao.findAll()) {
            System.out.println(course);
            List<Trainer> list = courseDao.findTrainersPerCourse(course.getCoId());
            printTrainers(list);
            System.out.println("\n");
        }

    }

    public static void printAssignmentsPerCourse() {
        CourseDao courseDao = new CourseDao();
        for (Course course : courseDao.findAll()) {
            System.out.println(course);
            List<Assignment> list = courseDao.findAssignmentsPerCourse(course.getCoId());
            printAssignments(list);
            System.out.println("\n");
        }
    }

    public static void printStudentsAssignmentsPerCourse() {
        StudentDao studentDao = new StudentDao();
        for (Student student : studentDao.findAll()) {
            studentDao.findAssignmentsCourses(student);
        }
    }

    
    //creating students
    public static void studentCreation() {
        StudentDao studentDao = new StudentDao();
        Scanner input = new Scanner(System.in);
        Boolean goBack = false;

        while (!goBack) {
            String firstName = " ";
            String lastName = " ";
            int tuition = 0;
            LocalDate date = LocalDate.now();
            System.out.println("FIRST NAME OF STUDENT :");
            firstName = tryString();
            System.out.println("LAST NAME OF STUDENT :");
            lastName = tryString();
            System.out.println("TUITION :");
            tuition = tryInt();
            System.out.println("DATE OF BIRTH : YYYY-MM-DD");
            date = tryDate();
            Student student = new Student(firstName, lastName, tuition, date);
            studentDao.create(student);
            System.out.println("\n");

            System.out.println("1 IF YOU WANT TO ADD ANOTHER STUDENT :");
            System.out.println("2 TO GO BACK :");
            String userChoice = input.nextLine();
            switch (userChoice) {
                case ("1"):
                    System.out.println("\n");
                    continue;
                case ("2"):
                    goBack = true;
            }

        }
    }

    
    //creating trainers
    public static void trainerCreation() {
        TrainerDao trainerDao = new TrainerDao();
        Scanner input = new Scanner(System.in);
        Boolean goBack = false;

        while (!goBack) {
            String firstName = " ";
            String lastName = " ";
            String subject = " ";
            System.out.println("FIRST NAME OF TRAINER :");
            firstName = tryString();
            System.out.println("LAST NAME OF TRAINER :");
            lastName = tryString();
            System.out.println("SUBJECT :");
            subject = tryString();

            Trainer trainer = new Trainer(firstName, lastName, subject);
            trainerDao.create(trainer);
            System.out.println("\n");
            System.out.println("1 IF YOU WANT TO ADD ANOTHER TRAINER :");
            System.out.println("2 TO GO BACK :");
            String userChoice = input.nextLine();
            switch (userChoice) {
                case ("1"):
                    System.out.println("\n");
                    continue;
                case ("2"):
                    goBack = true;
            }
        }
    }

    //creating courses
    public static void courseCreation() {
        CourseDao courseDao = new CourseDao();
        Scanner input = new Scanner(System.in);
        Boolean goBack = false;

        while (!goBack) {
            String title = " ";
            String stream = " ";
            String type = " ";
            LocalDate datestart = LocalDate.now();
            LocalDate dateend = LocalDate.now();
            System.out.println("TITLE :");
            title = tryStringNumber();
            System.out.println("STREAM :");
            stream = tryString();
            System.out.println("TYPE :");
            type = tryString();
            System.out.println("START DATE : YYYY-MM-DD");
            datestart = tryDate();
            System.out.println("END DATE : YYYY-MM-DD");
            dateend = tryDate();

            Course course = new Course(title, stream, type, datestart, dateend);
            courseDao.create(course);
            System.out.println("\n");
            System.out.println("1 IF YOU WANT TO ADD ANOTHER COURSE :");
            System.out.println("2 TO GO BACK :");
            String userChoice = input.nextLine();
            switch (userChoice) {
                case ("1"):
                    System.out.println("\n");
                    continue;
                case ("2"):
                    goBack = true;
            }
        }
    }

    //creating assignments
    public static void assignmentCreation() {
        Scanner input = new Scanner(System.in);
        Boolean goBack = false;
        AssignmentDao assignmentDao = new AssignmentDao();

        while (!goBack) {
            String type = " ";
            String description = " ";
            LocalDate datesub = LocalDate.now();
            int oralmark = 0;
            int totalmark = 0;
            Boolean checkSub = false;
            System.out.println("TYPE OF THE ASSIGNMENT :");
            type = tryString();
            System.out.println("DESCRIPTION OF THE ASSIGNMENT :");
            description = tryString();
            System.out.println("SUBMISSION DATE : YYYY-MM-DD");
            while (!checkSub) {
                datesub = tryDate();
                checkSub = checkSubDate(datesub);
            }
            System.out.println("ORAL MARK :");
            oralmark = tryInt();
            System.out.println("TOTAL MARK :");
            totalmark = tryInt();

            Assignment assignment = new Assignment(type, description, datesub, oralmark, totalmark);
            assignmentDao.create(assignment);
            System.out.println("\n");
            System.out.println("1 IF YOU WANT TO ADD ANOTHER ASSIGNMENT :");
            System.out.println("2 TO GO BACK :");
            String userChoice = input.nextLine();
            switch (userChoice) {
                case ("1"):
                    System.out.println("\n");
                    continue;
                case ("2"):
                    goBack = true;
            }
        }
    }

    //updating the fiels of a student
    public static void studentUpdate() {
        StudentDao studentDao = new StudentDao();
        System.out.println("PRESS THE NUMBER NEXT TO STID TO PICK THE STUDENT YOU WANT");
        printStudents(studentDao.findAll());
        Student student = studentDao.findById(tryInt());
        System.out.println(student);
        student=changeStudent(student);
        studentDao.update(student);
        System.out.println(student);
    }

    //updating the fields of a trainer
    public static void trainerUpdate() {
        TrainerDao trainerDao = new TrainerDao();
        System.out.println("PRESS THE NUMBER NEXT TO TRID TO PICK THE TRAINER YOU WANT");
        printTrainers(trainerDao.findAll());
        Trainer trainer = trainerDao.findById(tryInt());
        System.out.println(trainer);
        trainer=changeTrainer(trainer);
        trainerDao.update(trainer);
        System.out.println(trainer);
    }

    //updating the fields of a course
    public static void courseUpdate() {
        CourseDao courseDao = new CourseDao();
        System.out.println("PRESS THE NUMBER NEXT TO COID TO PICK THE COURSE YOU WANT");
        printCourses(courseDao.findAll());
        Course course = courseDao.findById(tryInt());
        System.out.println(course);
        course=changeCourse(course);
        courseDao.update(course);
        System.out.println(course);
    }

    //updating the fields of an assignment
    public static void assignmentUpdate() {
        AssignmentDao assignmentDao = new AssignmentDao();
        System.out.println("PRESS THE NUMBER NEXT TO ASID TO PICK THE ASSIGNMENT YOU WANT");
        printAssignments(assignmentDao.findAll());
        Assignment assignment = assignmentDao.findById(tryInt());
        System.out.println(assignment);
        assignment=changeAssignment(assignment);
        assignmentDao.update(assignment);
        System.out.println(assignment);
    }

    //deleting one of the 4 basic entities
    public static void studentDelete() {
        StudentDao studentDao = new StudentDao();
        System.out.println("PRESS THE NUMBER NEXT TO STID TO PICK THE STUDENT YOU WANT");
        printStudents(studentDao.findAll());
        studentDao.delete(tryInt());
    }

    public static void trainerDelete() {
        TrainerDao trainerDao = new TrainerDao();
        System.out.println("PRESS THE NUMBER NEXT TO TRID TO PICK THE TRAINER YOU WANT");
        printTrainers(trainerDao.findAll());
        trainerDao.delete(tryInt());
    }

    public static void courseDelete() {
        CourseDao courseDao = new CourseDao();
        System.out.println("PRESS THE NUMBER NEXT TO COID TO PICK THE COURSE YOU WANT");
        printCourses(courseDao.findAll());
        courseDao.delete(tryInt());
    }

    public static void assignmentDelete() {
        AssignmentDao assignmentDao = new AssignmentDao();
        System.out.println("PRESS THE NUMBER NEXT TO ASID TO PICK THE ASSIGNMENT YOU WANT");
        printAssignments(assignmentDao.findAll());
        assignmentDao.delete(tryInt());
    }

    

    //method to make sure the user will put an integer value from the keyboard
    public static int tryInt() {
        Scanner input = new Scanner(System.in);
        int number = 0;
        while (true) {
            try {
                number = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("NOT A NUMBER. TRY AGAIN.");
                continue;
            }
        }
        return number;
    }

    //method to make sure the user will put a LocalDate value from the keyboard
    public static LocalDate tryDate() {
        Scanner input = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        while (true) {
            String a = input.nextLine();
            try {
                date = LocalDate.parse(a);
                break;
            } catch (DateTimeException e) {
                System.err.println("NOT A DATE. TRY AGAIN.");
            }
        }
        return date;
    }

    //method to make sure the user will put a date that is between monday and friday from the keyboard
    public static Boolean checkSubDate(LocalDate date1) {
        Boolean result = true;
        DayOfWeek day = DayOfWeek.from(date1);
        switch (day.getValue()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                break;
            case 6:
                System.err.println("SUBMISSION DATE MUST BETWEEN MONDAY TO FRIDAY");
                result = false;
                break;
            case 7:
                System.err.println("SUBMISSION DATE MUST BETWEEN MONDAY TO FRIDAY");
                result = false;
                break;
        }
        return result;

    }

    //method to make sure the user will put a String value from the keyboard
    public static String tryString() {
        Scanner input = new Scanner(System.in);
        String a;
        while (true) {
            a = input.nextLine();
            if (Pattern.matches("[A-Za-z]*", a)) {
                break;
            } else {
                System.err.println("NOT A WORD. TRY AGAIN.");
                continue;
            }
        }
        return a;
    }
    
    //method to make sure the user will put an alphanumeric value from the keyboard
     public static String tryStringNumber() {
        Scanner input = new Scanner(System.in);
        String a;
        while (true) {
            a = input.nextLine();
            if (Pattern.matches("[A-Za-z0-9]+$", a)) {
                break;
            } else {
                System.err.println("NOT A WORD. TRY AGAIN.");
                continue;
            }
        }
        return a;
    }
    
     
     //methods to change the fields of the 4 basic entities
    public static Student changeStudent(Student student){
        System.out.println("WRITE NEW FIRST NAME");
        student.setFirstName(tryString());
        System.out.println("WRITE NEW LAST NAME");
        student.setLastName(tryString());
        System.out.println("WRITE NEW TUITION FEES");
        student.setTuitionFees(tryInt());
        System.out.println("WRITE NEW DATE OF BIRTH");
        student.setDateOfBirth(tryDate());
        return student;
    }
    
    public static Trainer changeTrainer(Trainer trainer){
        System.out.println("WRITE NEW FIRST NAME");
        trainer.setFirstName(tryString());
        System.out.println("WRITE NEW LAST NAME");
        trainer.setLastName(tryString());
        System.out.println("WRITE NEW SUBJECT");
        trainer.setSubject(tryString());
        return trainer;
    }
    
    public static Course changeCourse(Course course){
        System.out.println("WRITE NEW TITLE");
        course.setTitle(tryStringNumber());
        System.out.println("WRITE NEW STREAM");
        course.setStream(tryString());
        System.out.println("WRITE NEW TYPE");
        course.setType(tryString());
        System.out.println("WRITE NEW START DATE");
        course.setStartDate(tryDate());
        System.out.println("WRITE NEW END DATE");
        course.setEndDate(tryDate());
        return course;
    }
    
    public static Assignment changeAssignment(Assignment assignment){
        Boolean checkSub = false;
        LocalDate dateSub = LocalDate.now();
        System.out.println("WRITE NEW DESCRIPTION");
        assignment.setDescription(tryString());
        System.out.println("WRITE NEW TYPE");
        assignment.setType(tryString());
        System.out.println("WRITE NEW SUBMISSION DATE");
        while (!checkSub) {
            dateSub = tryDate();
            checkSub = checkSubDate(dateSub);
        }
        assignment.setSubDate(dateSub);
        System.out.println("WRITE NEW ORAL MARK");
        assignment.setOralMark(tryInt());
        System.out.println("WRITE NEW TOTAL MARK");
        assignment.setTotalMark(tryInt());
        return assignment;
    }

}
