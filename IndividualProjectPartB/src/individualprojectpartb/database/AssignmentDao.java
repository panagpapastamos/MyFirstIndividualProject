package individualprojectpartb.database;

import individualprojectpartb.entities.Assignment;
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

public class AssignmentDao extends GenericDetails implements GeneralCommands<Assignment> {

    public static final String FINDALL = "SELECT * FROM ASSIGNMENT";
    public static final String FINDBYID = "SELECT * FROM ASSIGNMENT WHERE ASID=?";
    private static final String INSERT = "INSERT INTO ASSIGNMENT (type,description,sub_date,oral_mark,total_mark) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE ASSIGNMENT SET type=? ,description=? ,sub_date=? ,oral_mark=?,total_mark=? WHERE ASID = ?";
    private static final String DELETE = "DELETE FROM ASSIGNMENT WHERE ASID = ?";

    @Override
    public List<Assignment> findAll() {
        List<Assignment> list = new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(FINDALL);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int AsId = rs.getInt("ASID");
                String type = rs.getString("type");
                String description = rs.getString("description");
                int oralMark = rs.getInt("oral_mark");
                int totalMark = rs.getInt("total_mark");
                Date subDate = rs.getDate("sub_date");
                LocalDate date = subDate.toLocalDate();
                Assignment assignment = new Assignment(AsId, type, description, date, oralMark, totalMark);
                list.add(assignment);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        closeConnections(rs,pstm,conn);
    }
        return list;
    }

    @Override
    public Assignment findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Assignment assignment = null;
        try {
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int AsId = rs.getInt("ASID");
            String type = rs.getString("type");
            String description = rs.getString("description");
            int oralMark = rs.getInt("oral_mark");
            int totalMark = rs.getInt("total_mark");
            Date subDate = rs.getDate("sub_date");
            LocalDate date = subDate.toLocalDate();
            assignment = new Assignment(AsId, type, description, date, oralMark, totalMark);
        } catch (SQLException ex) {
            String message = "Assignment with id " + id + " could not be found!!!";
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, message);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return assignment;
    }

    @Override
    public void create(Assignment assignment) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, assignment.getType());
            pstm.setString(2, assignment.getDescription());
            Date date = Date.valueOf(assignment.getSubDate());
            pstm.setDate(3, date);
            pstm.setInt(4, assignment.getOralMark());
            pstm.setInt(5, assignment.getTotalMark());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Assignment successfully created!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    @Override
    public void update(Assignment assignment) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setString(1, assignment.getType());
            pstm.setString(2, assignment.getDescription());
            Date date = Date.valueOf(assignment.getSubDate());
            pstm.setDate(3, date);
            pstm.setInt(4, assignment.getOralMark());
            pstm.setInt(5, assignment.getTotalMark());
            pstm.setInt(6, assignment.getAsId());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Assignment successfully updated!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Assignment successfully deleted!!");
            }
        } catch (SQLException ex) {
            System.err.println("Assignment not found");
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

}
