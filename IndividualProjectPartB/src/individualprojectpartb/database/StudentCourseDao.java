package individualprojectpartb.database;

import individualprojectpartb.entities.Course;
import individualprojectpartb.entities.Student;
import individualprojectpartb.entities.StudentCourse;
import individualprojectpartb.entities.StudentCourse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StudentCourseDao extends GenericDetails implements GeneralCommands<StudentCourse>{
    
    public static final String FINDALL = "SELECT * FROM STUDENT_COURSE";
    public static final String FINDBYID = "SELECT * FROM STUDENT_COURSE WHERE STCO_ID=?";
    private static final String INSERT = "INSERT INTO STUDENT_COURSE (student_ID,course_ID) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE STUDENT_COURSE SET student_id=?,course_id=? WHERE STCO_ID = ?";
    private static final String DELETE = "DELETE FROM STUDENT_COURSE WHERE STCO_ID = ?";

    //creating the list that connects students and courses
    @Override
    public List<StudentCourse> findAll() {
        List<StudentCourse> list = new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(FINDALL);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int CoAsId = rs.getInt("STCO_ID");
                int studentId = rs.getInt("student_ID");
                Student student = getStudentById(studentId);
                int courseId = rs.getInt("course_ID");
                Course course = getCourseById(courseId);
                StudentCourse CA = new StudentCourse(CoAsId, student, course);
                list.add(CA);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return list;

    }

    //finding a registration of a student attached to a course by the id
    @Override
    public StudentCourse findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        StudentCourse CA = null;
        StudentDao studentDao = new StudentDao();
        CourseDao courseDao = new CourseDao();
        try {
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int CoAsId = rs.getInt("STCO_ID");
            int studentId = rs.getInt("student_ID");
            Student student = getStudentById(studentId);
            int courseId = rs.getInt("course_ID");
            Course course = getCourseById(courseId);
            CA = new StudentCourse(CoAsId, student, course);
        } catch (SQLException ex) {
            String message = "There is no student and course together with id " + id;
            Logger.getLogger(StudentCourseDao.class.getName()).log(Level.SEVERE, message);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return CA;
    }

    //creating a registration of a student attending a course
    @Override
    public void create(StudentCourse CA) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(INSERT);
            pstm.setInt(1, CA.getStudent().getStId());
            pstm.setInt(2, CA.getCourse().getCoId());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Student has a course!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //updating a registration of a student attending a course
    @Override
    public void update(StudentCourse CA) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setInt(1, CA.getStudent().getStId());
            pstm.setInt(2, CA.getCourse().getCoId());
            pstm.setInt(3, CA.getSTCOID());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Student combined with course successfully updated!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //deleting a registration of student attending a course
    @Override
    public void delete(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Student combined with course successfully deleted!!");
            }
        } catch (SQLException ex) {
            System.err.println("Student with this course not found");
            Logger.getLogger(StudentCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }
    
    //method to get a specific student by the id
    private Student getStudentById(int id) {
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.findById(id);
        return student;
    }

    //method to get a specific course by the id
    private Course getCourseById(int id) {
        CourseDao courseDao = new CourseDao();
        Course course = courseDao.findById(id);
        return course;
    }

}
