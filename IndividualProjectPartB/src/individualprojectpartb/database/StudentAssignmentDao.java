package individualprojectpartb.database;

import individualprojectpartb.entities.Assignment;
import individualprojectpartb.entities.Student;
import individualprojectpartb.entities.StudentAssignment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentAssignmentDao extends GenericDetails implements GeneralCommands<StudentAssignment> {

    public static final String FINDALL = "SELECT * FROM STUDENT_ASSIGN";
    public static final String FINDBYID = "SELECT * FROM STUDENT_ASSIGN WHERE STAS_ID=?";
    private static final String INSERT = "INSERT INTO STUDENT_ASSIGN (student_ID,assignment_ID) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE STUDENT_ASSIGN SET student_id=?,assignment_id=? WHERE STAS_ID = ?";
    private static final String DELETE = "DELETE FROM STUDENT_ASSIGN WHERE STAS_ID = ?";

    //methos that prints the list that connects students and assignments
    @Override
    public List<StudentAssignment> findAll() {
        List<StudentAssignment> list = new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(FINDALL);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int CoAsId = rs.getInt("STAS_ID");
                int studentId = rs.getInt("student_ID");
                Student student = getStudentById(studentId);
                int assignmentId = rs.getInt("assignment_ID");
                Assignment assignment = getAssignmentById(assignmentId);
                StudentAssignment CA = new StudentAssignment(CoAsId, student, assignment);
                list.add(CA);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentAssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return list;

    }

    //method that finds a registration that connects student and assignment
    @Override
    public StudentAssignment findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        StudentAssignment CA = null;
        StudentDao studentDao = new StudentDao();
        AssignmentDao assignmentDao = new AssignmentDao();
        try {
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int CoAsId = rs.getInt("STAS_ID");
            int studentId = rs.getInt("student_ID");
            Student student = getStudentById(studentId);
            int assignmentId = rs.getInt("assignment_ID");
            Assignment assignment = getAssignmentById(assignmentId);
            CA = new StudentAssignment(CoAsId, student, assignment);
        } catch (SQLException ex) {
            String message = "There is no student and assignment together with id " + id;
            Logger.getLogger(StudentAssignmentDao.class.getName()).log(Level.SEVERE, message);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return CA;
    }

    //creating a registration of a student having an assignemnt
    @Override
    public void create(StudentAssignment CA) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(INSERT);
            pstm.setInt(1, CA.getStudent().getStId());
            pstm.setInt(2, CA.getAssignment().getAsId());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Student has an assignment!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentAssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //updating a registration of a student having an assignemnt by the id
    @Override
    public void update(StudentAssignment CA) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setInt(1, CA.getStudent().getStId());
            pstm.setInt(2, CA.getAssignment().getAsId());
            pstm.setInt(3, CA.getSTASID());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Student combined with assignment successfully updated!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentAssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //deleting a registration of a student having an assignemnt by the id
    @Override
    public void delete(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Student combined with assignment successfully deleted!!");
            }
        } catch (SQLException ex) {
            System.err.println("Student with this assignment not found");
            Logger.getLogger(StudentAssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //method to find a specific student by the id
    private Student getStudentById(int id) {
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.findById(id);
        return student;
    }

    //method to find a specific assignment by the id
    private Assignment getAssignmentById(int id) {
        AssignmentDao assignmentDao = new AssignmentDao();
        Assignment assignment = assignmentDao.findById(id);
        return assignment;
    }

}
