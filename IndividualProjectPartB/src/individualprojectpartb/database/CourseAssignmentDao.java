package individualprojectpartb.database;

import individualprojectpartb.entities.Assignment;
import individualprojectpartb.entities.Course;
import individualprojectpartb.entities.CourseAssignment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseAssignmentDao extends GenericDetails implements GeneralCommands<CourseAssignment> {

    public static final String FINDALL = "SELECT * FROM COURSE_ASSIGN";
    public static final String FINDBYID = "SELECT * FROM COURSE_ASSIGN WHERE COAS_ID=?";
    private static final String INSERT = "INSERT INTO COURSE_ASSIGN (course_ID,assignment_ID) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE COURSE_ASSIGN SET course_id=?,assignment_id=? WHERE COAS_ID = ?";
    private static final String DELETE = "DELETE FROM COURSE_ASSIGN WHERE COAS_ID = ?";

    //creating the list that connects courses and assignments
    @Override
    public List<CourseAssignment> findAll() {
        List<CourseAssignment> list = new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(FINDALL);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int CoAsId = rs.getInt("COAS_ID");
                int courseId = rs.getInt("course_ID");
                Course course = getCourseById(courseId);
                int assignmentId = rs.getInt("assignment_ID");
                Assignment assignment = getAssignmentById(assignmentId);
                CourseAssignment CA = new CourseAssignment(CoAsId, course, assignment);
                list.add(CA);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseAssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return list;

    }

    //find a a registration of a assignment attached to a course by the id
    @Override
    public CourseAssignment findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        CourseAssignment CA = null;
        CourseDao courseDao = new CourseDao();
        AssignmentDao assignmentDao = new AssignmentDao();
        try {
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int CoAsId = rs.getInt("COAS_ID");
            int courseId = rs.getInt("course_ID");
            Course course = getCourseById(courseId);
            int assignmentId = rs.getInt("assignment_ID");
            Assignment assignment = getAssignmentById(assignmentId);
            CA = new CourseAssignment(CoAsId, course, assignment);
        } catch (SQLException ex) {
            String message = "There is no course and assignment together with id " + id;
            Logger.getLogger(CourseAssignmentDao.class.getName()).log(Level.SEVERE, message);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return CA;
    }

    //creating a registration of an assignment attached to a course
    @Override
    public void create(CourseAssignment CA) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(INSERT);
            pstm.setInt(1,CA.getCourse().getCoId());
            pstm.setInt(2,CA.getAssignment().getAsId());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Course has an assignment!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseAssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //updating an existing registration of an assignment attached to a course
    @Override
    public void update(CourseAssignment CA) {
         Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setInt(1,CA.getCourse().getCoId());
            pstm.setInt(2,CA.getAssignment().getAsId());
            pstm.setInt(3, CA.getCOASID());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Course combined with assignment successfully updated!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseAssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //deleting a registration of an assignment attached to a course
    @Override
    public void delete(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Course combined with assignment successfully deleted!!");
            }
        } catch (SQLException ex) {
            System.err.println("Course with this assignment not found");
            Logger.getLogger(CourseAssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //method to find a specif course by the id
    private Course getCourseById(int id) {
        CourseDao courseDao = new CourseDao();
        Course course = courseDao.findById(id);
        return course;
    }

    //method to find a specific assignment by the id
    private Assignment getAssignmentById(int id) {
        AssignmentDao assignmentDao = new AssignmentDao();
        Assignment assignment = assignmentDao.findById(id);
        return assignment;
    }
}
