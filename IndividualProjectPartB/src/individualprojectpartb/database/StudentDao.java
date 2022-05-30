package individualprojectpartb.database;

import static individualprojectpartb.database.AssignmentDao.FINDALL;
import individualprojectpartb.entities.Assignment;
import individualprojectpartb.entities.Course;
import individualprojectpartb.entities.Student;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDao extends GenericDetails implements GeneralCommands<Student>{
    
    public static final String FINDALL="SELECT * FROM STUDENT";
    public static final String FINDBYID="SELECT * FROM STUDENT WHERE STID=?";
    private static final String INSERT = "INSERT INTO STUDENT (f_name,l_name,tuition_fees,date_of_birth) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE STUDENT SET f_name=? ,l_name=? ,tuition_fees=? ,date_of_birth=? WHERE stid = ?";
    private static final String DELETE = "DELETE FROM student WHERE STID = ?";
    private static final String FINDASSIGNMENTSCOURSEPERONESTUDENT="select course.COID,course.title, course.stream, course.type, course.start_date,course.end_date,"
    + "assignment.ASID,assignment.type,assignment.description,assignment.sub_date,assignment.oral_mark,assignment.total_mark from course_assign "
    + "inner join course on course_assign.course_id=course.COID inner join assignment on course_assign.assignment_id=assignment.ASID"
            + " inner join student_assign on student_assign.assignment_id=assignment.ASID inner join student on student_assign.student_id=student.STID AND student.STID=?";
    private static final String FINDSTUDENTSINMULTIPLECOURSES="SELECT student.stid, student.f_name, student.l_name, student.tuition_fees,student.date_of_birth " +
"from student inner join (SELECT student_id FROM student_course group by student_id having count(student_id)>1) AS ST ON student.STID=ST.student_id";

    //creating a list of all students
    @Override
    public List<Student> findAll() {
        List<Student> list =new ArrayList();
            Connection conn=getConnection();
            PreparedStatement pstm=null;
            ResultSet rs = null;
        try {
            pstm=conn.prepareStatement(FINDALL);
            rs=pstm.executeQuery();
            while (rs.next()){
                int StId=rs.getInt("STID");
                String firstName=rs.getString("f_name");
                String lastName=rs.getString("l_name");
                int tuitionFees=rs.getInt("tuition_fees");
                Date dateOfBirth=rs.getDate("date_of_birth");
                LocalDate date=dateOfBirth.toLocalDate();
                Student student=new Student(StId,firstName,lastName,tuitionFees,date);
                list.add(student);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        closeConnections(rs,pstm,conn);
    }
        return list;
    }

    //finding a student by his id
    @Override
    public Student findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Student student = null;
        try {
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int StId=rs.getInt("STID");
                String firstName=rs.getString("f_name");
                String lastName=rs.getString("l_name");
                int tuitionFees=rs.getInt("tuition_fees");
                Date dateOfBirth=rs.getDate("date_of_birth");
                LocalDate date=dateOfBirth.toLocalDate();
                student=new Student(StId,firstName,lastName,tuitionFees,date);
        } catch (SQLException ex) {
            String message = "Student with id " + id + " could not be found!!!";
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, message);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return student;
    }

    //creating a new student
    @Override
    public void create(Student student) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, student.getFirstName());
            pstm.setString(2, student.getLastName());
            pstm.setInt(3, student.getTuitionFees());
            Date dateOfBirth = Date.valueOf(student.getDateOfBirth());
            pstm.setDate(4, dateOfBirth);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Student successfully created!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //updating an existing student
    @Override
    public void update(Student student) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setString(1, student.getFirstName());
            pstm.setString(2, student.getLastName());
            pstm.setInt(3, student.getTuitionFees());
            Date dateOfBirth=Date.valueOf(student.getDateOfBirth());
            pstm.setDate(4, dateOfBirth);
            pstm.setInt(5, student.getStId());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Student successfully updated!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //delete an existing student by his id
    @Override
    public void delete(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Student successfully deleted!!");
            }
        } catch (SQLException ex) {
            System.err.println("Student not found");
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }
    
    //method that finds the assignments and the courses a student has and prints them
    public void findAssignmentsCourses(Student student) {
        List<Assignment> listA = new ArrayList();
        List<Course> listC=new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(FINDASSIGNMENTSCOURSEPERONESTUDENT);
            pstm.setInt(1,student.getStId());
            rs = pstm.executeQuery();
            while (rs.next()) {
                int CoId = rs.getInt("COID");
                String title = rs.getString("title");
                String stream = rs.getString("stream");
                String type = rs.getString(4);
                Date date1 = rs.getDate("start_date");
                LocalDate startDate = date1.toLocalDate();
                Date date2 = rs.getDate("end_date");
                LocalDate endDate = date2.toLocalDate();
                Course course = new Course(CoId, title, stream, type, startDate, endDate);
                if (!listC.contains(course)){
                    listC.add(course);
                }
                
                int AsId = rs.getInt("ASID");
                String typeA = rs.getString(8);
                String description = rs.getString("description");
                int oralMark = rs.getInt("oral_mark");
                int totalMark = rs.getInt("total_mark");
                Date subDate = rs.getDate("sub_date");
                LocalDate date = subDate.toLocalDate();
                Assignment assignment = new Assignment(AsId, typeA, description, date, oralMark, totalMark);
                listA.add(assignment);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        closeConnections(rs,pstm,conn);
    }
        prinStudentAssignmentCourse(student,listC,listA);
    }
    
    //printing method of the assignments and courses a student has
    public static void prinStudentAssignmentCourse(Student student,List <Course> listC,List<Assignment> listA){
        System.out.println("Student " + student + "has ");
        for (Course course : listC) {
            System.out.println(course);
        }
        for (Assignment assignment : listA) {
            System.out.println(assignment);
        }
        System.out.println("\n");
    }
    
    //method to create a list of students who attend more than one course
    public List<Student> findStudentsInMultipleCourses() {
        List<Student> list =new ArrayList();
            Connection conn=getConnection();
            PreparedStatement pstm=null;
            ResultSet rs = null;
        try {
            pstm=conn.prepareStatement(FINDSTUDENTSINMULTIPLECOURSES);
            rs=pstm.executeQuery();
            while (rs.next()){
                int StId=rs.getInt("STID");
                String firstName=rs.getString("f_name");
                String lastName=rs.getString("l_name");
                int tuitionFees=rs.getInt("tuition_fees");
                Date dateOfBirth=rs.getDate("date_of_birth");
                LocalDate date=dateOfBirth.toLocalDate();
                Student student=new Student(StId,firstName,lastName,tuitionFees,date);
                list.add(student);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        closeConnections(rs,pstm,conn);
    }
        return list;
    }
    
}
